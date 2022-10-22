package com.xaohii.chat.utils.common;

import com.xaohii.chat.netty.Message;
import com.xaohii.chat.repository.entity.po.MessagePo;
import com.xaohii.chat.repository.entity.vo.MessageVo;

import java.text.SimpleDateFormat;

public class P2vUtil {

	public static MessageVo convertMessagePo2Vo(MessagePo messagePo) {
		MessageVo messageVo = new MessageVo();
		messageVo.setMessage(messagePo.getMessage());
		messageVo.setTime(messagePo.getTime());
		messageVo.setUserName(messagePo.getUserName());
		messageVo.setToUserName(messagePo.getToUserName());
		return messageVo;
	}

	public static MessagePo convertMessage2Po(Message message) {
		MessagePo messagePo = new MessagePo();
		messagePo.setMessage(message.getMessage());
		messagePo.setType(message.getType());
		messagePo.setToUserName(message.getToUserName());
		messagePo.setToUserId(message.getToUserId());
		messagePo.setUserName(message.getUserName());
		messagePo.setUserId(message.getUserId());
		return messagePo;
	}
}
