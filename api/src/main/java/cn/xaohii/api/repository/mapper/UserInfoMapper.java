package cn.xaohii.api.repository.mapper;

import cn.xaohii.api.repository.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
	List<UserInfo> queryAllUserInfo();
}
