package com.xaohii.flow.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	@Autowired
	private RedisTemplate<String, Object> objectRedisTemplate;

	@Autowired
	private RedisTemplate<String, Integer> integerRedisTemplate;

	public void put(String key, Object value) {
		objectRedisTemplate.opsForValue().set(key, value);
	}

	public Object getObjectValue(String key) {
		return objectRedisTemplate.opsForValue().get(key);
	}

	public void put(String key, Integer value) {
		integerRedisTemplate.opsForValue().set(key, value);
	}

	public Integer getIntegerValue(String key) {
		return integerRedisTemplate.opsForValue().get(key);
	}

}