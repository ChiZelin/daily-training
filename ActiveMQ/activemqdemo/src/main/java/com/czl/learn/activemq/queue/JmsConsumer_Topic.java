package com.czl.learn.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.test.TestComponent;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import java.io.IOException;

public class JmsConsumer_Topic {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {

  /*      ActiveMQConnectionFactory activeMQConnectionFactory =
            new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session =
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        MessageConsumer messageConsumer = session.createConsumer(topic);

//        while(true) {
//            //TextMessage textMessage = (TextMessage) messageConsumer.receive(3000L);
//            TextMessage textMessage = (TextMessage) messageConsumer.receive();
//
//            if(null != textMessage){
//                System.out.println("消费者接收消息： " + textMessage.getText());
//            }else {
//                break;
//            }
//        }
//
//        messageConsumer.close();
//        session.close();
//        connection.close();

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

        messageConsumer.setMessageListener((Message message)->{
            if(null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println("消费者接收消息： " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

        messageConsumer.close();
        session.close();
        connection.close();*/




        /*
        * 持久化订阅
        * */
        System.out.println("*****z4");
        ActiveMQConnectionFactory activeMQConnectionFactory =
            new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z4");  //设置订阅者id

        Session session =
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark....."); //
        connection.start();

        Message message = topicSubscriber.receive();

        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("******收到的持久化 topic: " + textMessage.getText());
            message = topicSubscriber.receive();
        }


        session.close();
        connection.close();


    }
}
