package com.xaohii.swagger.service;

import com.xaohii.swagger.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl {
	public static ArrayList<User> users = new ArrayList<>();

	static {
		users.add(new User("xiaoming","123456"));
		users.add(new User("xiaoli","13579"));
	}

	public String index() {
		StringBuilder allUser = new StringBuilder();
		users.forEach(user -> {
			allUser.append(user.getName());
			allUser.append(",");
		});
		return allUser.toString();
	}
}
