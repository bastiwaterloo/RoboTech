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

public class SendAbsageToQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Get current Order from execution
        Cart abgesagterAuftrag = (Cart) execution.getVariable("cart"); 
        
        // Create JSON with information about the cancelled order
        JsonObject absageJson = Json.createObjectBuilder()
        		.add("abgesagterAuftrag", 
    				 Json.createObjectBuilder()       				 	
    				 .add("Auftrags-ID", abgesagterAuftrag.getAuftragsID())
                     .add("Kunden-ID", abgesagterAuftrag.getKundenID())
                     .build()
                 )
                 .build();
        //Send to queue
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("AbgesagteAuftraegeQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage(absageJson.toString());
            producer.send(message);

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
