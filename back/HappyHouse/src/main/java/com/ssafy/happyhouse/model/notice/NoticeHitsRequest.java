package com.ssafy.happyhouse.model.notice;

public class NoticeHitsRequest {

	private String refreshToken;
	private int noticeId;
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	
	@Override
	public String toString() {
		return "NoticeHitsRequest [refreshToken=" + refreshToken + ", noticeId=" + noticeId + "]";
	}
		
}
