package com.czl.dubbo.service.impl;

import com.czl.dubbo.bean.UserAddress;
import com.czl.dubbo.service.UserService;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {

	public List<UserAddress> getUserAddressList(int userId) {
		UserAddress address1 = new UserAddress(1,"孙悟空", "花果山水帘洞");
		UserAddress address2 = new UserAddress(2,"猪八戒", "高老庄");

		return Arrays.asList(address1,address2);
	}
}
