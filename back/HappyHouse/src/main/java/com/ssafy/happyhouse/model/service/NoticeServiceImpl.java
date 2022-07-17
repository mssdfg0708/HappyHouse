package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.model.notice.CreateNoticeRequest;
import com.ssafy.happyhouse.model.notice.ModifyNoticeRequest;
import com.ssafy.happyhouse.model.notice.Notice;
import com.ssafy.happyhouse.model.notice.NoticeHitsRequest;
import com.ssafy.happyhouse.model.notice.NoticeHitsResponse;
import com.ssafy.happyhouse.model.notice.UpdateHits;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	private NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List<Notice> findAllNotice() {
		List<Notice> data = noticeMapper.findAllNotice();
		return data;
	}

	@Override
	public int createNotice(CreateNoticeRequest request) {
		
		int writerId = request.getWriterId();
		String writerName = request.getWriterName();
		String title = request.getTitle();
		String description = request.getDescription();
		
		Notice notice = new Notice(writerId, writerName, title, description, 0);
		System.out.println(notice);
		int result = noticeMapper.createNotice(notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeId) {
		int result = noticeMapper.deleteNotice(noticeId);
		return result;
	}

	@Override
	public int modifyNotice(ModifyNoticeRequest request) {
		int result = noticeMapper.modifyNotice(request);
		return result;
	}

	@Override
	public Notice findNoticeById(int noticeId) {
		Notice data = noticeMapper.findNoticeById(noticeId);
		return data;
	}

	@Override
	public NoticeHitsResponse noticeHits(NoticeHitsRequest request) {
		int noticeId = request.getNoticeId();
		NoticeHitsResponse response = new NoticeHitsResponse();
		response.setHits(-1);
		Notice notice = noticeMapper.findNoticeById(noticeId);
		if (notice == null) {
			response.setHits(-1);
			return response;
		}

		List<NoticeHitsRequest> visited = noticeMapper.isVisited(request);
		
		if (!visited.isEmpty()) {
			response.setHits(notice.getHits());
			return response;
		}
		
		noticeMapper.addVisit(request);
		long hits = notice.getHits() + 1;
		UpdateHits updateHits = new UpdateHits(noticeId, hits);
		noticeMapper.updateHits(updateHits);
		notice = noticeMapper.findNoticeById(noticeId);
		response.setHits(notice.getHits());
		
		return response;
	}
}
