package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.user.CreateUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserResponse;
import com.ssafy.happyhouse.model.user.User;
import com.ssafy.happyhouse.model.user.loginRequest;

public interface UserService {

	int createUser(CreateUserRequest request);
	User login(loginRequest request);
	void saveRefreshToken(String string, String refreshToken);
	String getRefreshToken(String email);
	void deleRefreshToken(String email);
	void logout(String email);
	ModifyUserResponse modifyUser(ModifyUserRequest request);
	List<User> getUserList(String refreshToken);
	int deleteUser(loginRequest request);
	int exileUser(User request);
	int newPassword(User request);
}
