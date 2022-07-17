package com.ssafy.happyhouse.model.house;

public class FavoriteAptListRequest {

	private String email;
	private String refreshToken;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Override
	public String toString() {
		return "FavoriteAptListRequest [email=" + email + ", refreshToken=" + refreshToken + "]";
	}
	
}
