package cn.xaohii.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 消息体中包含：消息，
 * */
@Data
public class Message {
	// 消息体
	@JsonProperty(value = "message")
	private String message;

	// 发送者ID
	@JsonProperty(value = "sendUserId")
	private String sendUserId;

	// 接收者ID
	@JsonProperty(value = "acceptUserId")
	private String acceptUserId;

}
