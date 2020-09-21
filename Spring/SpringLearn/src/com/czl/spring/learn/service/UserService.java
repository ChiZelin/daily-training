package com.czl.spring.learn.service;

import com.czl.spring.learn.dao.UserDao;
import com.czl.spring.learn.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserService {

    @Resource(name = "userDao1")
    private UserDao userDao;

    @Value(value = "haha")
    private String name;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public void update(){
        userDao.update();

        System.out.println(name);

       /* UserDao dao = new UserDaoImpl();
        dao.update();*/
    }
}
