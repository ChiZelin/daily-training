package com.czl.spring.learn.aopanno;

import org.springframework.stereotype.Component;

@Component
public class User {
	public void add() {
		System.out.println("add...");
	}
}
