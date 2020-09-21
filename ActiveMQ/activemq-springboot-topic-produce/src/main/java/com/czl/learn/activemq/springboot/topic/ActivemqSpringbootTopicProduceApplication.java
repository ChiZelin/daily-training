package com.czl.learn.activemq.springboot.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActivemqSpringbootTopicProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqSpringbootTopicProduceApplication.class, args);
    }

}
