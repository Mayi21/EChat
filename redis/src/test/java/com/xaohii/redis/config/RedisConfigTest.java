package com.xaohii.redis.config;

import com.xaohii.redis.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {
	@Autowired
	private RedisTemplate<String, String> strRedisTemplate;
	@Resource
	private RedisTemplate<String, Object> objectRedisTemplate;

	@Test
	public void testString() {
		strRedisTemplate.opsForValue().set("strKey", "zwqh");
		System.out.println(strRedisTemplate.opsForValue().get("strKey"));
	}

	@Test
	public void testSerializable() {
		UserEntity user=new UserEntity();
		user.setAge(10);
		user.setName("朝雾轻寒");
		user.setSex(true);
		objectRedisTemplate.opsForValue().set(user.getName(), user);
		UserEntity user2 = (UserEntity) objectRedisTemplate.opsForValue().get("user");
		System.out.println(user2.toString());
	}
} 
