package com.guruji.users.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guruji.users.requestvm.SignupVM;
import com.guruji.users.service.SignUpService;

/**
 * <h1>SignUpController</h1>
 * <p>
 * Register new user
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Validated
@RestController
@RequestMapping("/api")
public class SignUpController {

	private final SignUpService signUpService;

	public SignUpController(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	/**
	 * <h1>SignUpController</h1>
	 * <p>
	 * Register user in system with default user role
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 15-01-2021
	 */
	@PostMapping("/signUp")
	public ResponseEntity<Object> signUp(@RequestBody @Valid SignupVM signupVM) {
		return this.signUpService.signUp(signupVM);
	}
}
