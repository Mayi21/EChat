package cn.xaohii.controller;

import entity.AppResp;
import entity.UserInfo;
import cn.xaohii.utils.mail.MailServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/user")
@RestController
public class UserController {
	@Resource
	private UserInfoServiceImpl userInfoService;

	@Resource
	private MailServiceImpl mailService;

	@PostMapping(value = "/login")
	public AppResp login(@RequestBody UserInfo userInfo) {
		return userInfoService.login(userInfo);
	}

	@GetMapping(value = "/code")
	public void sendCode() {
		System.out.println(mailService.sendSignCodeMail("xaoohii@gmail.com"));
	}

}
