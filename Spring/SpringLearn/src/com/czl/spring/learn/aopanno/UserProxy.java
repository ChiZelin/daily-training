package com.czl.spring.learn.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class UserProxy {

	@Pointcut(value = "execution(* com.czl.spring.learn.aopanno.User.add(..))")
	public void pointdemo() {

	}

	@Before(value = "pointdemo()")
	public void before() {
		System.out.println("before.....");
	}

	@AfterReturning(value = "pointdemo()")
	public void afterReturning() {
		System.out.println("afterReturning.....");
	}

	@After(value = "pointdemo()")
	public void after() {
		System.out.println("after.....");
	}

	@AfterThrowing(value = "pointdemo()")
	public void afterThrowing() {
		System.out.println("afterThrowing.....");
	}

	@Around(value = "pointdemo()")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Around before.....");


		proceedingJoinPoint.proceed();


		System.out.println("Around after.....");
	}

}
