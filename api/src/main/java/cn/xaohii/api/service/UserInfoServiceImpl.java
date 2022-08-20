package cn.xaohii.api.service;

import cn.xaohii.api.repository.entity.UserInfo;
import cn.xaohii.api.repository.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl {
	@Resource
	private UserInfoMapper userInfoMapper;

	public List<UserInfo> queryAllUserInfo() {
		return userInfoMapper.queryAllUserInfo();
	}
}