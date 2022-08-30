package cn.xaohii.api.service;

import adapter.NettyClientAdapter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {
	@Async
	public void sendMessage(String msg) {
		NettyClientAdapter.sendMsg(msg);
	}
}
