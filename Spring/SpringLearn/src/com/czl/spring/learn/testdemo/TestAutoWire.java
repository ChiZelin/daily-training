package com.czl.spring.learn.testdemo;

import com.czl.spring.learn.autowire.Emp;
import com.czl.spring.learn.dao.UserDao;
import com.czl.spring.learn.dao.impl.UserDaoImpl;
import com.czl.spring.learn.service.UserService;
import config.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoWire {

	@Test
	public void testAutoWire() {
		ApplicationContext context =
			new AnnotationConfigApplicationContext(SpringConfig.class);

		UserService userService = context.getBean("userService", UserService.class);

		userService.update();


	}
}
