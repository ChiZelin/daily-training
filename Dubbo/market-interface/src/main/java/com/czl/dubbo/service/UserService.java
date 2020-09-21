package com.czl.dubbo.service;

import com.czl.dubbo.bean.UserAddress;

import java.util.List;

public interface UserService {

	public List<UserAddress> getUserAddressList(int userId);
}
