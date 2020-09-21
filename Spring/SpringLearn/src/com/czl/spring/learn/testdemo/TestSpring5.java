package com.czl.spring.learn.testdemo;

import com.czl.spring.learn.Course;
import com.czl.spring.learn.Student;
import com.czl.spring.learn.User;
import com.czl.spring.learn.bean.Emp;
import com.czl.spring.learn.bean.Orders;
import com.czl.spring.learn.factorybean.MyBean;
import com.czl.spring.learn.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring5 {

	@Test
	public void testAdd() {
		//1 加载Spring配置文件

		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean1.xml");

		//2获取配置创建的对象
		User user = context.getBean("user", User.class);

		user.printUser();


	}

	@Test
	public void testBean(){
		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean2.xml");

		UserService userService = context.getBean("userService", UserService.class);

		userService.update();
	}

	@Test
	public void testEmpBean(){
		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean4.xml");

		Emp emp = context.getBean("emp", Emp.class);

		emp.printEmp();
	}

	@Test
	public void testInjectCollection() {
		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean5.xml");

		Student student = context.getBean("student", Student.class);
		System.out.println(student);

		student = context.getBean("student", Student.class);
		System.out.println(student);
	}

	@Test
	public void testMyBean(){
		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean6.xml");

		Course course = context.getBean("myBean", Course.class);

		System.out.println(course);

	}

	@Test
	public void testLifecycle() {
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("bean7.xml");

		Orders orders = context.getBean("orders", Orders.class);

		System.out.println("4 get");

		context.close();
	}

}
