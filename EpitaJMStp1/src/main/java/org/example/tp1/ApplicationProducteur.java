package org.example.tp1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ApplicationProducteur {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue("queue_EPITA_essai");
            //Destination queue = session.createTopic("mon_TOPIC");
            TextMessage message = session.createTextMessage("Hello world ! Qui veut le 4ème nouveau message ?");
            MessageProducer producer = session.createProducer(queue);
            producer.send(message);
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
