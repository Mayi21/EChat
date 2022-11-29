package com.xaohii.swagger.controller;

import com.xaohii.swagger.pojo.User;
import com.xaohii.swagger.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author xaohii
 * @Date 2022/5/16 21:45
 */
@RestController
@Api(tags = "用户操作")
public class UserController {
	@Resource
	private UserServiceImpl userService;


	@ApiOperation(value = "用来获取用户列表", notes = "用来获取所有的用户信息")
	@GetMapping("/users")
	public String index(){
		return userService.index();
	}
//	@ApiOperation(value = "添加用户", notes = "添加用户信息")
//	@PostMapping("/user")
//	public Object add(User user){
//		return users.add(user);
//	}
//	@ApiOperation(value = "删除用户")
//	@DeleteMapping("/user/{id}")
//	public void delete(@PathVariable int id){
//		users.remove(id);
//	}


}
