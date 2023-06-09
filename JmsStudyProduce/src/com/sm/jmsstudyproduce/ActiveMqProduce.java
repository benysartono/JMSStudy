package com.sm.jmsstudyproduce;

import org.mortbay.jetty.Connector;
import java.net.URI;
//import java.net.URISyntaxException; 
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Queue; 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService; 
public class ActiveMqProduce {
    public static void main(String arg[]) throws Exception {
        //BrokerService broker = BrokerFactory.createBroker(new URI(
        //        "broker:(tcp://localhost:61616)"));
    			//"broker:(http://localhost:7766)"));
        //broker.start();
        Connection connection = null;
        try {
            // Producer
        	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("customerQueue");                  
            String payload = "Billing From DGT";
            Message msg = session.createTextMessage(payload);
            MessageProducer producer = session.createProducer(queue);
            System.out.println("Sending '" + payload + "'");
            producer.send(msg);             
            Thread.sleep(3000);
            //session.close();
        } finally {
            //if (connection != null) {
            //    connection.close();
            //}
            //broker.stop();
        }
    }
}