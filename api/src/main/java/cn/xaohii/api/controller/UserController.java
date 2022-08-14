package cn.xaohii.api.controller;

import cn.xaohii.api.service.MailServiceImpl;
import cn.xaohii.api.service.UserInfoServiceImpl;
import cn.xaohii.common.entity.AppResp;
import cn.xaohii.common.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/user")
@RestController
@Api(tags = "用户相关")
public class UserController {
	@Resource
	private UserInfoServiceImpl userInfoService;

	@Resource
	private MailServiceImpl mailService;

	@PostMapping(value = "/login")
	@ApiOperation(value = "登录")
	public AppResp login(@RequestBody UserInfo userInfo) {
		return userInfoService.login(userInfo);
	}

	@GetMapping(value = "/code")
	@ApiOperation(value = "发送验证码")
	public void sendCode() {
		System.out.println(mailService.sendSignCodeMail("xaoohii@gmail.com"));
	}

}
