package com.czl.spring.learn.aopanno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Order(1)
public class PersonProxy {

	@Before(value = "execution(* com.czl.spring.learn.aopanno.User.add(..))")
	public void before() {
		System.out.println("Person before.....");
	}
}
