package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.mapper.ReplyMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.reply.Reply;
import com.ssafy.happyhouse.model.reply.ReplyRequest;
import com.ssafy.happyhouse.model.user.User;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyMapper replyMapper;
	private final UserMapper userMapper;

	@Autowired
	public ReplyServiceImpl(ReplyMapper replyMapper, UserMapper userMapper) {
		this.replyMapper = replyMapper;
		this.userMapper = userMapper;
	}

	@Override
	public List<Reply> getReplyList(int noticeId) {
		List<Reply> data = replyMapper.getReplyList(noticeId);
		return data;
	}

	@Override
	public int createReply(ReplyRequest request) {
		int result = replyMapper.createReply(request);
		return result;
	}

	@Override
	public int modifyReply(ReplyRequest request) {
		String email = request.getEmail();
		Reply reply = replyMapper.getReply(request);
		
		if (validateUser(email, reply))
			return replyMapper.modifyReply(request);
		else
			return 0;
	}

	@Override
	public int deleteReply(ReplyRequest request) {
		String email = request.getEmail();
		Reply reply = replyMapper.getReply(request);
		
		if (validateUser(email, reply))
			return replyMapper.deleteReply(request);
		else
			return 0;
	}

	public boolean validateUser(String email, Reply reply) {
		User user = userMapper.selectUserByEmail(email);
		if (user == null)
			return false;
		
		int userId = user.getId();
		return reply.getWriterId() == userId;
	}
}
