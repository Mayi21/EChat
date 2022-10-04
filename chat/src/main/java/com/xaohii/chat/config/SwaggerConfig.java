package com.xaohii.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"cn.xaohii.api.controller"})
public class SwaggerConfig {
	@Bean
	public Docket customDocket(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		Contact contact = new Contact ("xiaolizi",
				"https://github.com",
					"null");
		return new ApiInfoBuilder()
				.title("API")
				.description("API")
				.contact (contact)
				.version ("1.1.0")
				.build();
	}
}
