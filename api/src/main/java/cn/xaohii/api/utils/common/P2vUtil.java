package cn.xaohii.api.utils.common;

import cn.xaohii.api.repository.entity.po.MessagePo;
import cn.xaohii.api.repository.entity.vo.MessageVo;

public class P2vUtil {

	public static MessageVo convertVo(MessagePo messagePo) {
		MessageVo messageVo = new MessageVo();
		messageVo.setMessage(messagePo.getMessage());
		messageVo.setTime(messagePo.getTime());
		messageVo.setUserName(messagePo.getUserName());
		messageVo.setToUserName(messagePo.getToUserName());
		return messageVo;
	}
}