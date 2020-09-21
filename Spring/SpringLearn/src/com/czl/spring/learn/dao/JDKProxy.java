package com.czl.spring.learn.dao;

import com.czl.spring.learn.dao.impl.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
	public static void main(String[] args) {
		Class[] interfaces = {UserDao.class};


		UserDao userDao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(),
			interfaces, new UserDaoProxy(new UserDaoImpl()));


		userDao.update();

	}
}

class UserDaoProxy implements InvocationHandler{

	private Object object;

	public UserDaoProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("方法执行之前......");


		Object res = method.invoke(object, args);


		System.out.println("方法执行之后......" + object);





		return res;
	}
}