package com.ssafy.happyhouse.model.user;

public class CreateUserRequest {

	private String email;
	private String password;
	private String userName;
	private String address;
	
	public CreateUserRequest(String email, String password, String userName, String address) {
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.address = address;
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
			
	@Override
	public String toString() {
		return "CreateUserRequest [email=" + email + ", password=" + password + ", userName=" + userName + ", address="
				+ address + "]";
	}

	
}
