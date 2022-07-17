package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.user.ModifyUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserResponse;
import com.ssafy.happyhouse.model.user.User;
import com.ssafy.happyhouse.model.user.loginRequest;
import com.ssafy.happyhouse.model.user.loginResponse;

@Mapper
public interface UserMapper {

	String getSalt(String email);
	int createUser(User user);
	User login(loginRequest request);
	void saveRefreshToken(Map<String, String> map);
	String getRefreshToken(String email);
	void logout(String email);
	void modifyUser(ModifyUserRequest request);
	ModifyUserResponse selectUser(String email);
	List<User> validateEmail(String email);
	List<User> getUserList();
	int deleteUser(loginRequest request);
	void deleteFavoriteApt(String email);
	String getUserByRefreshToken(String refreshToken);
	int exileUser(String email);
	List<User> validateUserName(String userName);
	User selectUserByEmail (String email);
	int newPassword(User request);
}
