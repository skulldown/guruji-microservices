package com.guruji.users.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guruji.users.requestvm.RelationVM;

/**
 * <h1>RelationService</h1>
 * <p>
 * Manage user relation information
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 18-01-2021
 */
@Service
public interface RelationService {

	public ResponseEntity<Object> allRelation();
	
	public ResponseEntity<Object> relationAdd(RelationVM relationVM);
	
	public ResponseEntity<Object> relationUpdate(RelationVM relationVM);
	
	public ResponseEntity<Object> relationDelete(Long realtionId);

}
