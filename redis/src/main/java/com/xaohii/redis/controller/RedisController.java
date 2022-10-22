package com.xaohii.redis.controller;


import com.xaohii.redis.entity.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1")
public class RedisController {

	private RedisTemplate<String, UserEntity> redisTemplate;

	@PostMapping(value = "/insert")
	public void insertUser() {
		UserEntity user = new UserEntity();
		user.setName("xiaohei");
		user.setAge(11);
		user.setSex(true);
		redisTemplate.opsForValue().set(user.getName(), user);
	}

}
