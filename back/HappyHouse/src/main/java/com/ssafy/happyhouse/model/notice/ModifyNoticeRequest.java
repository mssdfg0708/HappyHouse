package com.ssafy.happyhouse.model.notice;

public class ModifyNoticeRequest {
	
	private int noticeId;
	private String title;
	private String description;
	
	public ModifyNoticeRequest(int noticeId, String title, String description) {
		this.noticeId = noticeId;
		this.title = title;
		this.description = description;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ModifyNoticeRequest [noticeId=" + noticeId + ", title=" + title + ", description=" + description + "]";
	}

}
