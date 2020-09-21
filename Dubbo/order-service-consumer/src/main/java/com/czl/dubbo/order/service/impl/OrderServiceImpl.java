package com.czl.dubbo.order.service.impl;

import com.czl.dubbo.bean.UserAddress;
import com.czl.dubbo.service.OrderService;
import com.czl.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserService userService;

	public void initOrder(int userid) {
		System.out.println(userid);

		List<UserAddress> addressList = userService.getUserAddressList(userid);

		System.out.println(addressList);
	}
}
