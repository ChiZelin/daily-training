package com.czl.learn.activemq;

import com.czl.learn.activemq.springboot.produce.QueueProduce;
import com.czl.learn.activemq.springboot.ActivemqSpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = ActivemqSpringbootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {

    @Resource
    private QueueProduce queueProduce;

    @Test
    public void testSend() throws Exception {
        queueProduce.produceMsg();
    }

    @Test
    public void testScheduledSend() throws Exception {
        queueProduce.produceMsgScheduled();
    }
}
