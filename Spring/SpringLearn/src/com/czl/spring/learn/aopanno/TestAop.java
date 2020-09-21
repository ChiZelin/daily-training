package com.czl.spring.learn.aopanno;

import com.czl.spring.learn.config.ConfigAop;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	@Test
	public void testAopAnnotation() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);

		User user = context.getBean("user", User.class);

		user.add();

	}


}
