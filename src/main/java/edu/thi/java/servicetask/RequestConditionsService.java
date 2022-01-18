package edu.thi.java.servicetask;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class RequestConditionsService implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		  String user = ActiveMQConnection.DEFAULT_USER;
	        String password = ActiveMQConnection.DEFAULT_PASSWORD;
	        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	        Destination destination;
	        
	                
	        // JSON mit fehlenden Auftragsdaten erstellen
	        JsonObject fehlendeDatenJson = Json.createObjectBuilder()
	        		.add("processInstanceId", execution.getProcessInstanceId())
	                 .build();

	        
	        try {
	            Connection connection = null;
	            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
	            connection = connectionFactory.createConnection();
	            connection.start();
	            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            destination = session.createQueue("conditionRequestQueue");
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
