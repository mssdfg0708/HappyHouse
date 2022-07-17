package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.notice.ModifyNoticeRequest;
import com.ssafy.happyhouse.model.notice.Notice;
import com.ssafy.happyhouse.model.notice.NoticeHitsRequest;
import com.ssafy.happyhouse.model.notice.UpdateHits;

@Mapper
public interface NoticeMapper {

	List<Notice> findAllNotice();
	int createNotice(Notice notice);
	int deleteNotice(int noticeId);
	int modifyNotice(ModifyNoticeRequest request);
	Notice findNoticeById(int noticeId);
	List<NoticeHitsRequest> isVisited(NoticeHitsRequest request);
	void addVisit(NoticeHitsRequest request);
	void updateHits(UpdateHits updateHits);
}
