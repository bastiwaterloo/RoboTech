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
//import javax.json.*;

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
        JsonObject auftragsJson = Json.createObjectBuilder()
        		.add("auftrag", 
        				 Json.createArrayBuilder()
        				 	.Json.createArrayBuilder()
        				 .add("auftragsid", "IDPL Colony")
                         .add("roboterart", "Hyderabad")
                         .add("menge", "500072")
                         .add("status", "")
                         .build()
        				
                .add("empAge", "25")
                .add("empSalary", "40000")
                     
                .build();
*/
        
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("\t\"item\": [\n");
        for (int i=0; i< auftragsliste.size(); i++) {
        	Auftrag auftrag = auftragsliste.get(i);
            sb.append("\t\t{\n");
            sb.append("\t\t\t\"auftragsid\":\"" + auftrag.getAuftragsID() + "\",\n");
            sb.append("\t\t\t\"roboterart\":\"" + auftrag.getRoboterart() + "\",\n");
            sb.append("\t\t\t\"menge\":" + auftrag.getMenge() + ",\n");
            sb.append("\t\t\t\"status\":\"" + auftrag.getStatus() + "\"\n");
            sb.append((i == auftragsliste.size()-1) ? "\t\t}\n" : "\t\t},\n");
        }
        sb.append("\t]\n");
        sb.append("}\n");
        
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("AuftragsdatenQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(sb.toString());
            producer.send(message);

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
