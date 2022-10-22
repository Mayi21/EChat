package com.xaohii.redis.controller;


import com.xaohii.redis.entity.UserEntity;
import com.xaohii.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1")
public class RedisController {
	@Autowired
	private RedisUtil redisUtil;

	@PostMapping(value = "/insert")
	public void insertUser() {
		UserEntity user = new UserEntity();
		user.setName("xiaohei");
		user.setAge(11);
		user.setSex(true);
		redisUtil.put(user.getName(), user);
	}

}
