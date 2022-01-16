/*
 * 
 * Klasse erstellt von: Lukas Keßler
 * 
 */

package edu.thi.java.servicetask;
 
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


import edu.thi.jpa.beans.Cart;


public class SendFehlendeAuftragsdatenToQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Relevante Variablen aus dem Prozesskontext auslesen
        Cart fehlendeDaten = (Cart) execution.getVariable("input_cart"); 
        String anmerkung = (String) execution.getVariable("anmerkungSachbearbeiter"); //TODO VariableLocal?
        
                
        // JSON mit fehlenden Auftragsdaten erstellen
        JsonObject fehlendeDatenJson = Json.createObjectBuilder()
        		.add("fehlendeDaten", 
    				 Json.createObjectBuilder()       				 	
    				 .add("Auftrags-ID", fehlendeDaten.getAuftragsID())
                     .add("Kunden-ID", fehlendeDaten.getKundenID())
                     .add("Spezifikation des Kunden", fehlendeDaten.getSpezifikation())
                     .add("Anmerkung bzgl. fehlender Daten", anmerkung)
                     .build()
                 )
                 .build();

        
        
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FehlendeAuftragsdaten");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(fehlendeDatenJson.toString());
            producer.send(message);

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
