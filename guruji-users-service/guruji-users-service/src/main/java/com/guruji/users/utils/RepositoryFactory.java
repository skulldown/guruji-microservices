package com.guruji.users.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guruji.auth.repository.RelationEntityRepository;
import com.guruji.auth.repository.RoleEntityRepository;
import com.guruji.auth.repository.UserEntityRepository;

@Component
public class RepositoryFactory {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	@Autowired
	private RelationEntityRepository relationEntityRepository;

	public UserEntityRepository getUserEntityRepository() {
		return userEntityRepository;
	}

	public RoleEntityRepository getRoleEntityRepository() {
		return roleEntityRepository;
	}

	public RelationEntityRepository getRelationEntityRepository() {
		return relationEntityRepository;
	}
}