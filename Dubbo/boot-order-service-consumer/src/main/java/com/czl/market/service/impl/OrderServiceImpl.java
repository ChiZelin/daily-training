package com.czl.market.service.impl;

import com.czl.dubbo.bean.UserAddress;
import com.czl.dubbo.service.OrderService;
import com.czl.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class OrderServiceImpl implements OrderService {


	@DubboReference(version = "${demo.service.version}", url = "${demo.service.url}")
	UserService userService;

	@Override
	public void initOrder(int userid) {
		System.out.println(userid);

		List<UserAddress> addressList = userService.getUserAddressList(userid);

		System.out.println("######" + addressList);
	}
}
