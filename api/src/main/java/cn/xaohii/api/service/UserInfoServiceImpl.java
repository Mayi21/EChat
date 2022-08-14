package cn.xaohii.api.service;

import cn.xaohii.common.entity.AppResp;
import cn.xaohii.common.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl {

	public AppResp login(UserInfo userInfo) {
		String userName = userInfo.getUserName();
		AppResp appResp = new AppResp();
		return appResp;
	}
}