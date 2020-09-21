package com.czl.spring5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney() {
        String sql = "Update t_account set money = money - ? where username = ?";

        jdbcTemplate.update(sql, 100, "linda");

        System.out.println("addMoney");
    }

    @Override
    public void reduceMoney() {
        String sql = "Update t_account set money = money + ? where username = ?";

        jdbcTemplate.update(sql, 100, "andy");

        System.out.println("reduceMoney");
    }
}