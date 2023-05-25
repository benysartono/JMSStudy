package com.sm.jmsstudyconsume;

import org.mortbay.jetty.Connector;

import com.sm.jmsstudyconsume.ConsumerMessageListener;

import java.net.URI;
//import java.net.URISyntaxException; 
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Queue; 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService; 
public class ActiveMqConsume {
    public static void main(String arg[]) throws Exception {
        Connection connection = null;
        try {
        	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("TEST.FOO");                  
            // Consumer1 subscribes to customerTopic
            MessageConsumer consumer1 = session.createConsumer(queue);
            //consumer1.setMessageListener(new ConsumerMessageListener("Consumer1"));             
            connection.start();                  
            TextMessage textMsg = (TextMessage) consumer1.receive();
            System.out.println("Fetching Msg '" + textMsg + "'");
            System.out.println("Received: " + textMsg.getText());
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            //broker.stop();
        }
    }
}