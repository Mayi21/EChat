package com.xaohii.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class MainController {

	@GetMapping("/test")
	public String test() {
		return "Hello world";
	}
}
