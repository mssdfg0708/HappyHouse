package com.ssafy.happyhouse.model.user;

public class ModifyUserRequest {
	
	private String email;
	private String userName;
	private String address;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "ModifyUserRequest [email=" + email +", userName=" + userName + ", address="
				+ address + "]";
	}

}
