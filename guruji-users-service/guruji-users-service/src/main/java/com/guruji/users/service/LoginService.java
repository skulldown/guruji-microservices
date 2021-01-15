package com.guruji.users.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guruji.users.requestvm.LoginVM;

@Service
public interface LoginService {
	
	public ResponseEntity<?> authorizeUser(LoginVM loginVM);

}
