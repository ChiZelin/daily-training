package com.czl.market.controller;

import com.czl.dubbo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/initOrder")
    public void initOrder(@RequestParam("uid")String userId) {
        System.out.println("Hello.............");
        orderService.initOrder(1);
    }

}
