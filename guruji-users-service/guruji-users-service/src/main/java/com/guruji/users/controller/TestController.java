package com.guruji.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class TestController {

	@GetMapping("/test-admin")
	public String testAdmin() {
		return "Hello Admin!!!";
	}

	@GetMapping("/test-user")
	public String testUser() {
		return "Hello User!!!";
	}

}
