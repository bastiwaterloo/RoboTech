/*
 * 
 Autor dieser Klasse: Lukas Keßler
 *
 */

package edu.thi.java.getConditionsFromQueue;



import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class ReceiveConditionsFromQueue implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Topic topic;
        
        
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            
            Session subSession = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            topic = subSession.createTopic("konditionenQueue");
            
            MessageConsumer subscriber = subSession.createConsumer(topic);
            Message msg = (Message) subscriber.receive();
            if(msg instanceof TextMessage) {
            	String body = ((TextMessage) msg).getText();
            	System.out.println(body);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
