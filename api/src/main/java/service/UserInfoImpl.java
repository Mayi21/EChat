package service;

import entity.AppResp;
import entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImpl {

	public AppResp login(UserInfo userInfo) {
		String userName = userInfo.getUserName();
		AppResp appResp = new AppResp();
		return appResp;
	}
}
