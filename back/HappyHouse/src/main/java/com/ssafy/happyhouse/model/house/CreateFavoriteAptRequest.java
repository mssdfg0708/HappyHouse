package com.ssafy.happyhouse.model.house;

public class CreateFavoriteAptRequest {

	private String email;
	private String aptCode;
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		return "CreateFavoriteAptRequest [email=" + email + ", aptCode=" + aptCode + "]";
	}
	
}
