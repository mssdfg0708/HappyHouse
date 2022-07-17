package com.ssafy.happyhouse.model.notice;

public class CreateNoticeRequest {
	
	private int writerId;
	private String writerName;
	private String title;
	private String description;
	
	public CreateNoticeRequest(int writerId, String writerName, String title, String description) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		this.description = description;
	}
	
	public int getWriterId() {
		return writerId;
	}
	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
		return "CreateNoticeRequest [writerId=" + writerId + ", writerName=" + writerName + ", title=" + title
				+ ", description=" + description + "]";
	}
	
}
