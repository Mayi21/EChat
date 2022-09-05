package cn.xaohii.api;

import cn.xaohii.api.service.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiApplicationTests {

	@Resource
	private UserInfoServiceImpl userInfoService;

	@Test
	public void testForUserInfoService() {
		userInfoService.insertUserData();
	}

}
