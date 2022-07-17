package com.ssafy.happyhouse.model.notice;

public class NoticeHitsResponse {

	private long hits;

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "NoticeHitsResponse [hits=" + hits + "]";
	}
	
}
