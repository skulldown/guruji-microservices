package com.guruji.users.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guruji.users.requestvm.LoginVM;
import com.guruji.users.service.LoginService;

@Controller
@RequestMapping("/api")
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authorize(@Valid @RequestBody LoginVM loginVM) {
		return this.loginService.authorizeUser(loginVM);
	}
	
}
