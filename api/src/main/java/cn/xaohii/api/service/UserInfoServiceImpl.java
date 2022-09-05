package cn.xaohii.api.service;

import cn.xaohii.api.repository.entity.UserInfo;
import cn.xaohii.api.repository.mapper.UserInfoMapper;
import javafx.scene.input.DataFormat;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserInfoServiceImpl {
	@Resource
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
}