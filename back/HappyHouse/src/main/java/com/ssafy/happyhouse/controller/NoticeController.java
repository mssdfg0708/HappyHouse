package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.notice.CreateNoticeRequest;
import com.ssafy.happyhouse.model.notice.ModifyNoticeRequest;
import com.ssafy.happyhouse.model.notice.Notice;
import com.ssafy.happyhouse.model.notice.NoticeHitsRequest;
import com.ssafy.happyhouse.model.notice.NoticeHitsResponse;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@CrossOrigin("*")
public class NoticeController {
	
	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("notice/noticelist")
	public List<Notice> findAllNotice() {
		List<Notice> noticeList = noticeService.findAllNotice();
		return noticeList;
	}
	
	@PostMapping("notice")
	public int createNotice(@RequestBody @Validated CreateNoticeRequest request) {
		int result = noticeService.createNotice(request);
		return result;
	}
	
	@DeleteMapping("/notice/{noticeId}")
	public int deleteNotice(@PathVariable("noticeId") int noticeId) {
		int result = noticeService.deleteNotice(noticeId);
		return result;
	}

	@PutMapping("/notice/{noticeId}")
	public int modifyNotice(@RequestBody @Validated ModifyNoticeRequest request) {
		int result = noticeService.modifyNotice(request);
		return result;
	}
	
	@GetMapping("/notice/{noticeId}")
	public Notice findNoticeById(@PathVariable("noticeId") int noticeId) {
		Notice notice = noticeService.findNoticeById(noticeId);
		return notice;
	}
	
	@PostMapping("notice/hits")
	public NoticeHitsResponse noticeHits(@RequestBody @Validated NoticeHitsRequest request) {
		NoticeHitsResponse response = noticeService.noticeHits(request);
		return response;
	}
	
}
