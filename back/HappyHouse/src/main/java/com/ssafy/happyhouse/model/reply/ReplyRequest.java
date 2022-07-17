package com.ssafy.happyhouse.model.reply;

public class ReplyRequest {
	
	private int replyId;
	private int noticeId; 
	private int writerId;
	private String writerName;
	private String replyDescription;
	private String dateTime;
	private String email;
	
	public ReplyRequest() {
		
	}

	public ReplyRequest(int noticeId, int writerId, String writerName, String replyDescription) {
		this.noticeId = noticeId;
		this.writerId = writerId;
		this.writerName = writerName;
		this.replyDescription = replyDescription;
	}

	public ReplyRequest(int replyId, String title, String replyDescription, String email) {
		this.replyId = replyId;
		this.replyDescription = replyDescription;
		this.email = email;
	}
	
	public ReplyRequest(int replyId, String email) {
		this.replyId = replyId;
		this.email = email;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
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

	public String getReplyDescription() {
		return replyDescription;
	}

	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
