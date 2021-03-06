package com.ssafy.happyhouse.model.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.user.CreateUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserResponse;
import com.ssafy.happyhouse.model.user.User;
import com.ssafy.happyhouse.model.user.loginRequest;
import com.ssafy.happyhouse.model.user.loginResponse;

import org.apache.ibatis.session.SqlSession;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private SqlSession sqlSession;
	private static final int SALT_SIZE = 16;
	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int createUser(CreateUserRequest request) {
		String email = request.getEmail();
		String userName = request.getUserName();
		List<User> duplictedUser = validateEmail(email);
		if (!duplictedUser.isEmpty()) {
			return 0;
		}
		duplictedUser = validateUserName(userName);
		if (!duplictedUser.isEmpty()) {
			return 0;
		}
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setUserName(request.getUserName());
		user.setAddress(request.getAddress());
		
		byte[] bytes = request.getPassword().getBytes();
		String SALT = null;
		try {
			SALT = getSALT();
			user.setPassword(Hashing(bytes, SALT));
			user.setSalt(SALT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = userMapper.createUser(user);
		return result;
	}
	
	private List<User> validateUserName(String userName) {
		List<User> duplicatedUser = userMapper.validateUserName(userName);
		return duplicatedUser;
	}

	public List<User> validateEmail(String email) {
		List<User> duplicatedUser = userMapper.validateEmail(email);
		return duplicatedUser;
	}
	
	// ????????? salt ??? ????????????
	private String getSalt(String email) {
		String salt = userMapper.getSalt(email);
		System.out.println("salt" + salt);
		return salt;
	}
	
	// ???????????? ??????  
	private String Hashing(byte[] password, String Salt) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 ??????????????? ?????? 
 
		// key-stretching
		for(int i = 0; i < 10000; i++) {
			// ??????????????? Salt ??? ?????? ????????? ????????? ?????? 
			String temp = Byte_to_String(password) + Salt;	
			// temp ??? ???????????? ???????????? md ??? ??????????????? 
			md.update(temp.getBytes());						
			// md ????????? ?????????????????? ?????? password ??? ???????????? 
			password = md.digest();							
		}
		
		return Byte_to_String(password);
	}
	
	// SALT ??? ??????  
	private String getSALT() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rnd.nextBytes(temp);
		
		return Byte_to_String(temp);
		
	}
	
	// ????????? ?????? 16????????? ??????????????? 
	private String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}

	@Override
	public User login(loginRequest request) {
		User loginUser = null;
		byte[] bytes = request.getPassword().getBytes();
		String salt = userMapper.getSalt(request.getEmail());
		if (salt == null) {
			return loginUser;
		}
		
		String password;
		try {
			password = Hashing(bytes, salt);
			request.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loginUser = userMapper.login(request);
		return loginUser;
	}

	@Override
	public void saveRefreshToken(String email, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("refreshToken", refreshToken);
		System.out.println(refreshToken.length());
		System.out.println(email + refreshToken);
		userMapper.saveRefreshToken(map);
		
	}

	@Override
	public String getRefreshToken(String email) {
		return userMapper.getRefreshToken(email);
	}

	@Override
	public void logout(String email) {
		userMapper.logout(email);
	}

	@Override
	public void deleRefreshToken(String email) {
		
	}

	@Override
	public ModifyUserResponse modifyUser(ModifyUserRequest request) {
		String email = request.getEmail();		
		userMapper.modifyUser(request);
		ModifyUserResponse user = userMapper.selectUser(email);
		return user;
	}

	@Override
	public List<User> getUserList(String refreshToken) {
		List<User> userList = new ArrayList<>();
		System.out.println(refreshToken);
		if (validateAdmin(refreshToken)) {
			userList = userMapper.getUserList();
		}

		return userList;
	}

	@Override
	public int deleteUser(loginRequest request) {
		String email = request.getEmail();
		String salt = userMapper.getSalt(email);
		if (salt == null)
			return 0;
		
		byte[] bytes = request.getPassword().getBytes();
		String password = null;
		try {
			password = Hashing(bytes, salt);
			request.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setPassword(password);
		int result = userMapper.deleteUser(request);
		if (result == 1)
			userMapper.deleteFavoriteApt(email);

		return result;
	}

	@Override
	public int exileUser(User request) {
		int result = 0;
		String email = request.getEmail();
		String refreshToken = request.getRefreshToken();
		
		if (validateAdmin(refreshToken))
			result = userMapper.exileUser(email);
			
		return result;
	}
	
	public boolean validateAdmin(String refreshToken) {
		String userName = userMapper.getUserByRefreshToken(refreshToken);
		System.out.println("?????????" + userName);

		if (userName.equals("?????????"))
			return true;
		else
			return false;
	}

	@Override
	public int newPassword(User request) {
		byte[] bytes = request.getPassword().getBytes();
		String salt = userMapper.getSalt(request.getEmail());
		if (salt == null)
			return 0;

		try {
			String password = Hashing(bytes, salt);
			request.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = userMapper.newPassword(request);
		return result;
	}
}
