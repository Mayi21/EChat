package com.xaohii.chat.service;

import com.xaohii.chat.repository.entity.po.MessagePo;
import com.xaohii.chat.repository.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl {
	@Autowired
	private MessageMapper messageMapper;

	public List<MessagePo> queryAllMessage() {
		return messageMapper.queryAll();
	}

	public void insertData(MessagePo messagePo) {
		messageMapper.insertData(messagePo);
	}

	public List<MessagePo> queryByUserId(Long userId) {
		return messageMapper.queryByUserId(userId);
	}

	public List<MessagePo> queryByToUserId(Long toUserId) {
		return messageMapper.queryByToUserId(toUserId);
	}
}
