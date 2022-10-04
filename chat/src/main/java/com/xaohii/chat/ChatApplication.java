package com.xaohii.chat;

import com.xaohii.chat.netty.NettyServer;
import com.xaohii.chat.utils.common.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xaohii.chat.repository.mapper")
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
		NettyServer nettyServer = SpringContextUtils.getBean(NettyServer.class);
		nettyServer.bind();
	}

}
