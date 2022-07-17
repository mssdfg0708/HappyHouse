package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.reply.Reply;
import com.ssafy.happyhouse.model.reply.ReplyRequest;

@Mapper
public interface ReplyMapper {

	List<Reply> getReplyList(int noticeId);
	Reply getReply(ReplyRequest request);
	int createReply(ReplyRequest request);
	int modifyReply(ReplyRequest request);
	int deleteReply(ReplyRequest request);
}
