package com.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class consommateur1 {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            //connection.setClientID("1");    // uniquement pour les TOPIC
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            Destination queue = session.createQueue("queue_EPITA_essai");
            //Topic topic = session.createTopic("mon_TOPIC"); // uniquement pour les TOPIC

            MessageConsumer consumer = session.createConsumer(queue);
            //MessageConsumer consumer = session.createDurableSubscriber(topic, "consumer-2"); // uniquement TOPIC
            consumer.setMessageListener(new MonConsumer());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
