package com.czl.spring.learn.dao.impl;

import com.czl.spring.learn.dao.UserDao;
import org.springframework.stereotype.Component;

@Component(value = "userDao1")
public class UserDaoImpl implements UserDao {

    public void update() {
        System.out.println("do update........");
    }
}
