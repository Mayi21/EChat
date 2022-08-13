package cn.xaohii.controller;

import entity.AppResp;
import entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl {

	public AppResp login(UserInfo userInfo) {
		String userName = userInfo.getUserName();
		AppResp appResp = new AppResp();
		return appResp;
	}
}