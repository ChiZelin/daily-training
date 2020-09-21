package com.czl.learn.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

public class JmsConsumer {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //public static final String ACTIVEMQ_URL = "nio://127.0.0.1:5671";
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException, IOException {

        ActiveMQConnectionFactory activeMQConnectionFactory =
            new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

//        Session session =
//            connection.createSession(true, Session.AUTO_ACKNOWLEDGE); // 1

        Session session =
            connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer messageConsumer = session.createConsumer(queue);


        while(true) {
            //TextMessage textMessage = (TextMessage) messageConsumer.receive(3000L);
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);

            if(null != textMessage){
                System.out.println("消费者接收消息： " + textMessage.getText());
                textMessage.acknowledge();
            }else {
                break;
            }
        }

        messageConsumer.close();
        //session.commit(); // 与 1 一起构成消费端事务
        session.close();
        connection.close();

//        //监听方式，异步非阻塞
//        messageConsumer.setMessageListener(new MessageListener() {
//
//            @Override
//            public void onMessage(Message message) {
//                if(null != message && message instanceof TextMessage) {
//                    TextMessage textMessage = (TextMessage) message;
//
//                    try {
//                        System.out.println("消费者接收消息： " + textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        System.in.read();
//
//        messageConsumer.close();
//        session.close();
//        connection.close();

    }
}
