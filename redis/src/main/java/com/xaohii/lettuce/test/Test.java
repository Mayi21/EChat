package com.xaohii.lettuce.test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Test {
	public static void main(String[] args) {
		RedisClient redisClient = RedisClient.create("redis://qwer@123@localhost:6379/0");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();

		syncCommands.set("key", "Hello, Redis!");

		connection.close();
		redisClient.shutdown();
	}
}
