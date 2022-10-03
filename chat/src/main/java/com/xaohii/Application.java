package com.xaohii;

import com.xaohii.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(value = "com.xaohii.repository.mapper")
public class Application {
	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).run(args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.bind();
	}
}
