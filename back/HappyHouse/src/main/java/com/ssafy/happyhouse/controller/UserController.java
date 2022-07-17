package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.reply.Message;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.model.service.jwtService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;
import com.ssafy.happyhouse.model.user.CreateUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserRequest;
import com.ssafy.happyhouse.model.user.ModifyUserResponse;
import com.ssafy.happyhouse.model.user.User;
import com.ssafy.happyhouse.model.user.loginRequest;
import com.ssafy.happyhouse.model.user.loginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("*")
public class UserController {
	
	private UserService userService;
	private jwtServiceImpl jwtService;

	@Autowired
	public UserController(UserService userService, jwtServiceImpl jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}
	
	@PostMapping("user")
	public int createUser(@RequestBody @Validated CreateUserRequest request){
		int result = userService.createUser(request);
		return result;
	}
	
	@PutMapping("user")
	public ModifyUserResponse modifyUser(@RequestBody @Validated ModifyUserRequest request) {
		System.out.println("Controller" + request.toString());
		ModifyUserResponse user = userService.modifyUser(request);
		return user;
	}
	
	@PostMapping("user/login")
	public loginResponse login(@RequestBody @Validated loginRequest request) {
		User result = null;
		result = userService.login(request);
		loginResponse loginUser = new loginResponse();
		if (result == null) {
			return loginUser;
		}
		
		loginUser.setId(result.getId());
		loginUser.setUserName(result.getUserName());
		loginUser.setAddress(result.getAddress());
		loginUser.setEmail(result.getEmail());
		loginUser.setPassword("secretPassword");
		loginUser.setAccessToken(jwtService.createAccessToken("email", request.getEmail()));
		String refreshToken = jwtService.createRefreshToken("email", request.getEmail());
		
		loginUser.setRefreshToken(refreshToken);
		System.out.println(loginUser.getEmail() + refreshToken);
		userService.saveRefreshToken(loginUser.getEmail(), refreshToken);
		
		return loginUser;
	}
	
	@PostMapping("user/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request) throws Exception{
		String SUCCESS = "success";
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		token = user.getRefreshToken();

		jwtService.checkToken(user.getRefreshToken());
		System.out.println("token" + " " + token);
		if(token.equals(userService.getRefreshToken(user.getEmail()))) {
			String accessToken= jwtService.createAccessToken("email", user.getEmail());
			resultMap.put("accessToken", accessToken);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping("user/logout/{email}")
	public ResponseEntity<?> logout(@PathVariable("email") String email) throws Exception{
		String SUCCESS = "success";
		String FAIL = "fail";
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			userService.logout(email);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			resultMap.put("message",FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);		
	}

	@GetMapping("user/userlist/{refreshToken}")
	public ResponseEntity<List<User>> getUserList(@PathVariable("refreshToken") String refreshToken) {
		List<User> userList = userService.getUserList(refreshToken);
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<List<User>> (userList, status);
	}
	
	@DeleteMapping("user")
	public Map<String, String> deleteUser(@RequestBody @Validated loginRequest request) {
		String SUCCESS = "success";
		String FAIL = "fail";
		String value = FAIL;
		Map<String, String> result = new HashMap<>();
		
		int status = userService.deleteUser(request);
		
		if (status == 1)
			value = SUCCESS;
			
		result.put("message", value);
		return result;
	}
	
	@DeleteMapping("user/exile")
	public Map<String, String> ExileUser(@RequestBody User request) {
		String SUCCESS = "success";
		String FAIL = "fail";
		String value = FAIL;
		Map<String, String> result = new HashMap<>();
		
		int status = userService.exileUser(request);
		
		if (status == 1)
			value = SUCCESS;
			
		result.put("message", value);
		return result;
	}
	
	@PutMapping("user/newpassword")
	public Message newPassword(@RequestBody User request) {
		Message response = new Message();
		int result = userService.newPassword(request);
		
		if (result == 1)
			response.setMessage("success");
		else
			response.setMessage("fail");
		
		return response;
	}
}
