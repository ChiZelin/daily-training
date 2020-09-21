package com.czl.learn.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsProduce {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //public static final String ACTIVEMQ_URL = "nio://127.0.0.1:5671";
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory =
            new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        //如果 事务设置为 true 后面应该用 session 提交才生效
        Session session =
            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer messageProducer = session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for(int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg---" + i);


            messageProducer.send(textMessage);
        }

        // 由于前面开启事务了
        messageProducer.close();
        //session.commit();
        session.close();
        connection.close();

        System.out.println("tx消息发布完成！！！！！！");

    }
}
