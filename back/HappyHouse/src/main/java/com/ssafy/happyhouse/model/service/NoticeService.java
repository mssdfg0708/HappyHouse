package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.notice.CreateNoticeRequest;
import com.ssafy.happyhouse.model.notice.ModifyNoticeRequest;
import com.ssafy.happyhouse.model.notice.Notice;
import com.ssafy.happyhouse.model.notice.NoticeHitsRequest;
import com.ssafy.happyhouse.model.notice.NoticeHitsResponse;

public interface NoticeService {

	List<Notice> findAllNotice();
	int createNotice(CreateNoticeRequest request);
	int deleteNotice(int noticeId);
	int modifyNotice(ModifyNoticeRequest request);
	Notice findNoticeById(int noticeId);
	NoticeHitsResponse noticeHits(NoticeHitsRequest request);
}
