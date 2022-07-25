package controller;


import entity.AppResp;
import entity.UserInfo;
import org.springframework.web.bind.annotation.*;
import service.UserInfoImpl;

import javax.annotation.Resource;

@RequestMapping(value = "/user")
@RestController
public class UserController {
	@Resource
	private UserInfoImpl userInfoImpl;
	@PostMapping(value = "/login")
	public AppResp login(@RequestBody UserInfo userInfo) {
		return userInfoImpl.login(userInfo);
	}

}
