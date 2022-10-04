package com.xaohii.chat.repository.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageVo {
	@JsonProperty(value = "user_name")
	private String userName;

	@JsonProperty(value = "to_user_name")
	private String toUserName;

	private String message;

	private String time;
}
