package cn.xaohii.api.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfo {
	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("user_name")
	private String userName;

	@JsonIgnore
	private String passwd;

	private String mail;

	private String phone;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("update_time")
	private String updateTime;
}
