package com.ssafy.happyhouse.model.reply;

public class Reply {

	private int replyId;
	private int noticeId; 
	private int writerId;
	private String writerName;
	private String replyDescription;
	private String dateTime;

	public Reply() {
		
	}	

	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
