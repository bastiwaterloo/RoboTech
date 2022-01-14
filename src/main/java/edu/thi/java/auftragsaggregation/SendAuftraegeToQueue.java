/*
 * 
 Autor dieser Klasse: Lukas Keßler
 *
 */

package edu.thi.java.auftragsaggregation;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.java.beans.Auftrag;

public class SendAuftraegeToQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Get the collected list from the process context
        @SuppressWarnings("unchecked")
        ArrayList<Auftrag> auftragsliste = (ArrayList<Auftrag>) execution.getVariable("auftragsliste");
                
        
        // Create JSON for message
        //TODO JSON-Builder einbauen
        
/*
 
 // Versuch der Schleife
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        
        for (int i=0; i< auftragsliste.size(); i++) {
	        Auftrag auftrag = auftragsliste.get(i);
	        
	        builder.add("auftragsid", "a");
	        builder.add("auftragsid", "a");
        }
        
        JsonObject auftragsJson = Json.createObjectBuilder()
        		.add("auftraege",
    				Json.createArrayBuilder()
    					.add(
    							
    						for (int i=0; i< auftragsliste.size(); i++) {
    					        Auftrag auftrag = auftragsliste.get(i);
    					        	
	    						Json.createObjectBuilder()       								
	    						.add("auftragsid", "a")
	    						.add("roboterart", "Hyderabad")
	    						.add("menge", "500072")
	    						.add("status", "")
	    						.build()
    						}
    					)
        			.build()
        		)
        		.build();
        
    //Funktioniert ohne Schleife

        JsonObject auftragsJson = Json.createObjectBuilder()
        		.add("auftraege",
    				Json.createArrayBuilder()
    					.add(
    						Json.createObjectBuilder()       								
    						.add("auftragsid", "a")
    						.add("roboterart", "Hyderabad")
    						.add("menge", "500072")
    						.add("status", "")
    						.build()
    					)
        			.build()
        		)
        		.build();

*/
        StringBuffer auftragsJson = new StringBuffer();
        auftragsJson.append("{\n");
        auftragsJson.append("\t\"item\": [\n");
        for (int i=0; i< auftragsliste.size(); i++) {
        	Auftrag auftrag = auftragsliste.get(i);
        	auftragsJson.append("\t\t{\n");
        	auftragsJson.append("\t\t\t\"auftragsid\":\"" + auftrag.getAuftragsID() + "\",\n");
        	auftragsJson.append("\t\t\t\"roboterart\":\"" + auftrag.getRoboterart() + "\",\n");
        	auftragsJson.append("\t\t\t\"menge\":" + auftrag.getMenge() + ",\n");
        	auftragsJson.append("\t\t\t\"status\":\"" + auftrag.getStatus() + "\"\n");
        	auftragsJson.append((i == auftragsliste.size()-1) ? "\t\t}\n" : "\t\t},\n");
        }
        auftragsJson.append("\t]\n");
        auftragsJson.append("}\n");
        

        
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("AuftragsdatenQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(auftragsJson.toString());
            producer.send(message);

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
