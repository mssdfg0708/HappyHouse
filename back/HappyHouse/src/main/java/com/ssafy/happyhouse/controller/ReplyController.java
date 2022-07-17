package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.reply.Message;
import com.ssafy.happyhouse.model.reply.Reply;
import com.ssafy.happyhouse.model.reply.ReplyRequest;
import com.ssafy.happyhouse.model.service.ReplyService;

@RestController
@CrossOrigin("*")
public class ReplyController {
	
	String SUCCESS = "success";
	String FAIL = "fail";
	
	private final ReplyService replyService;
	
	@Autowired
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("reply/{noticeId}")
	public List<Reply> getReplyList(@PathVariable("noticeId") int noticeId) {
		List<Reply> data = replyService.getReplyList(noticeId);
		return data;
	}
	
	@PostMapping("reply")
	public Message createReply(@RequestBody ReplyRequest request) {
		Message response = new Message();
		int result = replyService.createReply(request);
		
		if (result == 1)
			response.setMessage(SUCCESS);
		else
			response.setMessage(FAIL);
		
		return response;
	}
	
	@PutMapping("reply")
	public Message modifyReply (@RequestBody ReplyRequest request) {
		Message response = new Message();
		int result = replyService.modifyReply(request);
		
		if (result == 1)
			response.setMessage(SUCCESS);
		else
			response.setMessage(FAIL);
		
		return response;
	}
	
	@DeleteMapping("reply")
	public Message deleteReply(@RequestBody ReplyRequest request) {
		Message response = new Message();
		int result = replyService.deleteReply(request);
		
		if (result == 1)
			response.setMessage(SUCCESS);
		else
			response.setMessage(FAIL);
		
		return response;
	}
	
}
