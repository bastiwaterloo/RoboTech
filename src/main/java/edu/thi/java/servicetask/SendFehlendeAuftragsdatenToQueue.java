package edu.thi.java.servicetask;

/*
 * 
 * @Author Sebastian Waterloo
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

public class SendFehlendeAuftragsdatenToQueue implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//Queue data
		String user = ActiveMQConnection.DEFAULT_USER;
		String password = ActiveMQConnection.DEFAULT_PASSWORD;
		String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		Destination destination;

		// create JSON with missing order/customer data
		JsonObject fehlendeDatenJson = Json.createObjectBuilder()
				.add("fehlendeDaten",
						Json.createObjectBuilder()
								.add("E-Mail-Adresse", (String) execution.getVariable("formfield_email"))
								.add("Type", (String) execution.getVariable("formfield_type"))
								.add("SpezifikationKunde", (String) execution.getVariable("formfield_spezifikation"))
								.add("AnmerkungfehlendeDaten",
										(String) execution.getVariable("formfield_auftragsdaten_anmerkungen"))
								.build())
				.build();
		try {
			//Connect and send data to queue
			Connection connection = null;
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FehlendeAuftragsdatenQueue");
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
