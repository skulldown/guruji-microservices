package com.guruji.users.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guruji.users.requestvm.SignupVM;

/**
 * <h1>SignUpService</h1>
 * <p>
 * Manage user registration
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Service
public interface SignUpService {
	
	public ResponseEntity<Object> signUp(SignupVM signupVM);

}
