package com.xaohii.chat.repository.mapper;

import com.xaohii.chat.repository.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
	List<UserInfo> queryAllUserInfo();

	int insertUserData(UserInfo userInfo);
}
