package com.google.demo.service;

import com.google.demo.controller.request.UserRequest;
import com.google.demo.model.User;

public interface UserService {

	User resigter(UserRequest userRequest);
	User findUserByUsername(String username);
}
