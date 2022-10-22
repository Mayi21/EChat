package com.xaohii.flow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class MainController {

	@GetMapping(value = "/flow")
	public String getFlow() {
		return "1";
	}
}
