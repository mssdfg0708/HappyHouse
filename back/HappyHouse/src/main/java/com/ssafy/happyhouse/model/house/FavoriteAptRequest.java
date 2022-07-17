package com.ssafy.happyhouse.model.house;

public class FavoriteAptRequest {
	
	private String email;
	private Long aptCode;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAptCode() {
		return aptCode;
	}

	public void setAptCode(Long aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		return "AddFavoriteAptRequest [email=" + email + ", aptCode=" + aptCode + "]";
	}

}
