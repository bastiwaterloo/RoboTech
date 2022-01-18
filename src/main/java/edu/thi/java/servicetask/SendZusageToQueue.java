package edu.thi.java.servicetask;
/*
 * 
 * @Author Lukas Keßler
 * 
 */ 
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

public class SendZusageToQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	//Queue data
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Get current order
        Cart zugesagterAuftrag = (Cart) execution.getVariable("cart"); 
        
                
        // Create a json with information about the accepted order
        JsonObject zusageJson = Json.createObjectBuilder()
        		.add("zugesagterAuftrag",
    				 Json.createObjectBuilder()       				 	
    				 .add("Auftrags-ID", zugesagterAuftrag.getAuftragsID())
                     .add("Kunden-ID", zugesagterAuftrag.getKundenID())
                     .build()
                 )
                 .build();
        try {
        	//Connect and send data to queue
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("ZugesagteAuftraegeQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(zusageJson.toString());
            producer.send(message);

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
