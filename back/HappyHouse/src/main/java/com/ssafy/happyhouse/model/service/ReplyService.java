package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.reply.Reply;
import com.ssafy.happyhouse.model.reply.ReplyRequest;

public interface ReplyService {

	List<Reply> getReplyList(int noticeId);
	int createReply(ReplyRequest request);
	int modifyReply(ReplyRequest request);
	int deleteReply(ReplyRequest request);

}
