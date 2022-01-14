/*
 * 
 * Klasse erstellt von: Lukas Keßler
 * 
 */

package edu.thi.java.servicetasks;
 

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
import edu.thi.java.beans.Kunde;


public class SendFehlendeAuftragsdatenToQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Relevante Variablen aus dem Prozesskontext auslesen
        @SuppressWarnings("unchecked")
        int auftragsID = (int) execution.getVariable("auftragsID");
        int kundenID = (int) execution.getVariable("kundenID");
        String anmerkung = (String) execution.getVariable("fehlendeDaten");
                
        // JSON mit fehlenden Auftragsdaten erstellen
        JsonObject fehlendeDatenJson = Json.createObjectBuilder()
        		.add("fehlendeDaten", 
    				 Json.createObjectBuilder()       				 	
    				 .add("auftragsID", auftragsID)
                     .add("kundenID", kundenID)
                     .add("anmerkung", anmerkung)
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

