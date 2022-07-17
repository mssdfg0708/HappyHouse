package com.ssafy.happyhouse.model.notice;

public class UpdateHits {

	private int noticeId;
	private long hits;
	
	public UpdateHits(int noticeId, long hits) {
		this.noticeId = noticeId;
		this.hits = hits;
	}
	
	public UpdateHits() {
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}
	
}
