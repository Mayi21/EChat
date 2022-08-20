package cn.xaohii.api.repository.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class UserInfo {
	private String userId;

	private String userName;

	private String passwd;

	private String mail;

	private String phone;
}
