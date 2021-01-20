package com.guruji.users.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <h1>RoleService</h1>
 * <p>
 * Manage roles
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Service
public interface RoleService {
	
	public ResponseEntity<Object> assignUserRole(Long id, String role);

}
