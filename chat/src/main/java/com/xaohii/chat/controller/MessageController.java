package com.xaohii.chat.controller;

import com.xaohii.chat.netty.Message;
import com.xaohii.chat.netty.NettyClientAdapter;
import com.xaohii.chat.repository.entity.UserInfo;
import com.xaohii.chat.service.MessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "/msg")
@Api(value = "消息相关")
public class MessageController {
	@Resource
	private MessageServiceImpl messageService;

	@Autowired
	private NettyClientAdapter nettyClientAdapter;

	@PostMapping("/send")
	@ApiOperation(value = "发送消息")
	public void sendMsg(@RequestBody Message msg) {
		nettyClientAdapter.sendMessage(msg);
	}

	@PostMapping("/login")
	@ApiOperation(value = "用户登录")
	public void login(@RequestBody UserInfo userInfo) {
		// if user check is pass

	}
}
