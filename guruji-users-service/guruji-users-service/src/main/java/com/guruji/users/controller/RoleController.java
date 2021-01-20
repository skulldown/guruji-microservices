package com.guruji.users.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guruji.users.service.RoleService;

/**
 * <h1>SignUpController</h1>
 * <p>
 * Manage user roles
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Validated
@RestController
@RequestMapping("/api")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * <h1>SignUpController</h1>
	 * <p>
	 * Assign specific role to user
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 15-01-2021
	 */
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/role/assign/{id}/{role}")
	public ResponseEntity<Object> assignUserRole(@PathVariable(required = true) @NotEmpty @NotBlank Long id,
			@PathVariable(required = true) @NotEmpty @NotBlank String role) {
		return this.roleService.assignUserRole(id, role);
	}
}
