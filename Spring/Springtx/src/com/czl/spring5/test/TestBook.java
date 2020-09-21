package com.czl.spring5.test;

import com.czl.spring5.config.Config;
import com.czl.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TestBook {

	@Test
	public void testSpringTx() {
		ApplicationContext applicationContext =
			new ClassPathXmlApplicationContext("bean2.xml");

		UserService userService = applicationContext.getBean("userService", UserService.class);

		userService.accountMoney();
	}

	@Test
	public void testSpringTxAnnotation() {
		ApplicationContext applicationContext =
			new AnnotationConfigApplicationContext(Config.class);

		UserService userService =
			applicationContext.getBean("userService", UserService.class);

		userService.accountMoney();
	}

	@Test
	public void testGenericApplicationContext() {
		GenericApplicationContext context = new GenericApplicationContext();

		context.refresh();

		context.registerBean(User.class, ()-> new User());

		User user = (User)context.getBean("com.czl.spring5.test.User");

		System.out.println(user);
	}

	@Test
	public void testGenericApplicationContext2() {
		GenericApplicationContext context = new GenericApplicationContext();

		context.refresh();

		context.registerBean("user", User.class, ()-> new User());

		User user = (User)context.getBean("user");

		System.out.println(user);
	}


}
