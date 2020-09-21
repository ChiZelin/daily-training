package com.czl.spring.learn.aopxml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopXml {
	@Test
	public void testAopXml() {
		ApplicationContext applicationContext =
			new ClassPathXmlApplicationContext("beanaopxml.xml");

		Book book = applicationContext.getBean("book", Book.class);

		book.buy();
	}
}
