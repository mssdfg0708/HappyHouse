package com.ssafy.happyhouse.model.user;

public class loginResponse {
	
	private int id;
	private String email;
	private String password;
	private String userName;
	private String address;
	private String accessToken;
	private String refreshToken;
	
	public loginResponse() {
	}

	public loginResponse(int id, String email, String password, String userName, String address, String accessToken,
			String refreshToken) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.address = address;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	
}
