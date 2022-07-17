package com.ssafy.happyhouse.model.notice;

public class Notice {
	
	private int noticeId;
	private int writerId;
	private String writerName;
	private String title;
	private String description;
	private String dateTime;
	private long hits;
	
	

	public Notice() {
	}

	public Notice(int writerId, String writerName, String title, String description,
			long hits) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		this.description = description;
		this.hits = hits;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", writerId=" + writerId + ", writerName=" + writerName + ", title="
				+ title + ", description=" + description + ", dateTime=" + dateTime + ", hits=" + hits + "]";
	}

}
