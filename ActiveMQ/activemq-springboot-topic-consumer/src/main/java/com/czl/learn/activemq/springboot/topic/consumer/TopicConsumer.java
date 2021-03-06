package com.czl.learn.activemq.springboot.topic.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TopicConsumer {

    @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage) throws JMSException {

        System.out.println("消费者收到订阅的主题：" + textMessage.getText());

    }
}
