package com.xaohii.chat.repository.mapper;

import com.xaohii.chat.repository.entity.po.MessagePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
	List<MessagePo> queryAll();

	int insertData(MessagePo messagePo);

	List<MessagePo> queryByUserId(Long userId);

	List<MessagePo> queryByToUserId(Long toUserId);
}
