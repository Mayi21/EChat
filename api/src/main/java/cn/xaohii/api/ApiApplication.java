package cn.xaohii.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
@MapperScan("cn.xaohii.api.repository.mapper")
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
//		ConfigurableApplicationContext context = new SpringApplicationBuilder(ApiApplication.class).run(args);
//		NettyServer nettyServer = context.getBean(NettyServer.class);
//		nettyServer.bind();
	}

}
