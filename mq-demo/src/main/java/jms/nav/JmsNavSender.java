package jms.nav;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class JmsNavSender {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        Destination destination=new ActiveMQQueue("john_queue");
//        Destination destination=new ActiveMQTopic("john_topic");
        Connection connection=connectionFactory.createConnection();
        connection.start();
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer=session.createProducer(destination);
        TextMessage message=session.createTextMessage("Hello John12");
        producer.send(message);
//        message.acknowledge();
//        session.commit();
        connection.close();
    }
}