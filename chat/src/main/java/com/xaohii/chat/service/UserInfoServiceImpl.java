package com.xaohii.chat.service;

import com.xaohii.chat.repository.entity.UserInfo;
import com.xaohii.chat.repository.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserInfoServiceImpl {
	@Autowired
	private UserInfoMapper userInfoMapper;

	public List<UserInfo> queryAllUserInfo() {
		return userInfoMapper.queryAllUserInfo();
	}

	public void insertUserData() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("20220905000001");
		userInfo.setUserName("xiaoming");
		userInfo.setMail("xiaoming@qq.com");
		userInfo.setPasswd("12");
		userInfo.setPhone("111");
		Long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(time);
		userInfo.setCreateTime(date);
		userInfo.setUpdateTime(date);
		userInfoMapper.insertUserData(userInfo);
	}

	public void userLogin(UserInfo userInfo) {

	}
}