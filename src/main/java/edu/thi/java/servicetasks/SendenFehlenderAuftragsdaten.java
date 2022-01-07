package edu.thi.java.servicetasks;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.java.beans.AngebotBean;

public class SendenFehlenderAuftragsdaten implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Destination destination;
        
        // Get the collected list from the process context
        @SuppressWarnings("unchecked")
        ArrayList<Order> orderList = (ArrayList<Order>) execution.getVariable("orderList");
        
        // Create JSON for message
        // JSON mit besserer Lösung implementieren
        
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("\t\"item\": [\n");
        for (int i=0; i< orderList.size(); i++) {
            Order order = orderList.get(i);
            sb.append("\t\t{\n");
            sb.append("\t\t\t\"product\":\"" + order.getProduct() + "\",\n");
            sb.append("\t\t\t\"quantity\":" + order.getQuantity() + ",\n");
            sb.append("\t\t\t\"comment\":\"" + order.getComment() + "\"\n");
            sb.append((i == orderList.size()-1) ? "\t\t}\n" : "\t\t},\n");
        }
        sb.append("\t]\n");
        sb.append("}\n");
        
        try {
            Connection connection = null;
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("PDA_OrderQueue");
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
