package cn.xaohii.api.controller;

import cn.xaohii.api.service.MessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "/msg")
@Api(value = "消息相关")
public class MessageController {
	@Resource
	private MessageServiceImpl messageService;

	@PostMapping("/send")
	@ApiOperation(value = "发送消息")
	public void sendMsg(String msg) {
		messageService.sendMessage(msg);
	}
}
