package com.xaohii.chat.repository.entity.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessagePo {
	private Long id;

	@JsonProperty(value = "user_name")
	private String userName;

	@JsonProperty(value = "user_id")
	private Long userId;

	@JsonProperty(value = "to_user_name")
	private String toUserName;

	@JsonProperty(value = "to_user_id")
	private Long toUserId;

	private String message;

	private Integer type;

	private String time;
}
