package com.guruji.users.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guruji.users.requestvm.RelationVM;
import com.guruji.users.service.RelationService;

/**
 * <h1>RelationController</h1>
 * <p>
 * Manges user's relation information
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 18-01-2021
 */
@Validated
@RestController
@RequestMapping("/api/relation")
public class RelationController {

	private final RelationService relationService;

	public RelationController(RelationService relationService) {
		this.relationService = relationService;
	}

	
	/**
	 * <p>
	 * Get all relations of user
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 20-01-2021
	 */
	@GetMapping
	public ResponseEntity<Object> allRelation() {
		return this.relationService.allRelation();
	}
	
	/**
	 * <p>
	 * Add user relation
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 18-01-2021
	 */
	@PostMapping
	public ResponseEntity<Object> relationAdd(@RequestBody @Valid RelationVM relationVM) {
		return this.relationService.relationAdd(relationVM);
	}
	
	/**
	 * <p>
	 * Update user relation
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 20-01-2021
	 */
	@PutMapping
	public ResponseEntity<Object> relationUpdate(@RequestBody @Valid RelationVM relationVM) {
		return this.relationService.relationUpdate(relationVM);
	}
	
	/**
	 * <p>
	 * Delete user relation
	 * </p>
	 *
	 * @author Yogesh Makwana
	 * @version 1.0
	 * @since 20-01-2021
	 */
	@DeleteMapping("/{realtionId}")
	public ResponseEntity<Object> relationDelete(
			@PathVariable 
			@NotNull(message = "Relation user required") Long realtionId) {
		return this.relationService.relationDelete(realtionId);
	}
}
