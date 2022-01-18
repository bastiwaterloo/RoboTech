package edu.thi.java.maschinenevent;

import java.util.Map;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class CriticalEventSubscriber implements StatementSubscriber {
	
    // Grenzwert für einen kritischen Treibstofffüllstand einer Maschine 
    private static final String CRITICAL_FUEL_THRESHOLD = "20";
    
    
public String getStatement() {
        
        String crtiticalFuelExpression = "select * from FuellstandEvent "
                + "match_recognize ( "
                + "       measures A as fuellstand1, B as fuellstand2, C as fuellstand3 "
                + "       pattern (A B C) " 
                + "       define "
                + "               A as A.fuellstand < " + CRITICAL_FUEL_THRESHOLD +","
                + "				  B as B.fuellstand < " + CRITICAL_FUEL_THRESHOLD +"," 
                + "				  C as C.fuellstand < " + CRITICAL_FUEL_THRESHOLD + ")";
        
        return crtiticalFuelExpression;
    }
    

    
    /**
     * Listener method called when Esper has detected a pattern match.
     * @throws JMSException 
     */
    public void update(Map<String, FuellstandEvent> eventMap) throws JMSException {

        //Füllstand von Maschine 1
    	FuellstandEvent fuellstand1 = (FuellstandEvent) eventMap.get("fuellstand1");
    	
        //Füllstand von Maschine 2
        FuellstandEvent fuellstand2 = (FuellstandEvent) eventMap.get("fuellstand2");
        
        //Füllstand von Maschine 3
        FuellstandEvent fuellstand3 = (FuellstandEvent) eventMap.get("fuellstand3");
        

        StringBuilder sb = new StringBuilder();
        sb.append("***************************************");
        sb.append("\n* [ACHTUNG] Kritischer Füllstand erkannt! ");
        sb.append("\n* [Maschine 1: " + fuellstand1 + " ]");
        sb.append("\n* [Maschine 2: " + fuellstand2 + " ]");
        sb.append("\n* [Maschine 3: " + fuellstand3 + " ]");
        sb.append("\n***************************************");

        System.out.println(sb.toString());
        
        //Kritischen Füllstand an die Queue senden
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("KritischerFuellstandQueue");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            TextMessage msg = session.createTextMessage();
            msg.setLongProperty("fuellstand1", fuellstand1.getFuellstand());
            msg.setLongProperty("fuellstand2", fuellstand2.getFuellstand());
            msg.setLongProperty("fuellstand3", fuellstand3.getFuellstand());
            
            producer.send(msg);
            connection.close();
        }
        catch (JMSException ex) {
            ex.printStackTrace();
        }
        finally {
            if (producer != null)
                producer.close();
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
        }
    }
}
