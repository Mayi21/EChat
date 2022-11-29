package com.xaohii.chat.controller;//package cn.xaohii.api.controller;
//
//import cn.xaohii.api.repository.entity.UserInfo;
//import cn.xaohii.api.service.MailServiceImpl;
//import cn.xaohii.api.service.UserInfoServiceImpl;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RequestMapping(value = "/user")
//@RestController
//@Api(tags = "用户相关")
//public class UserController {
//	@Resource
//	private UserInfoServiceImpl userInfoService;
//
//	@Resource
//	private MailServiceImpl mailService;
//
//	@GetMapping(value = "/code")
//	@ApiOperation(value = "发送验证码")
//	public void sendCode() {
//		System.out.println(mailService.sendSignCodeMail("xaoohii@gmail.com"));
//	}
//
//	@GetMapping(value = "/userinfo")
//	@ApiOperation(value = "获取所有的用户信息")
//	public List<cn.xaohii.api.repository.entity.UserInfo> getAllUser() {
//		return userInfoService.queryAllUserInfo();
//	}
//
//	@PostMapping("/login")
//	@ApiOperation(value = "用户登录")
//	public void userLogin(@RequestBody UserInfo userInfo) {
//
//	}
//}
