package com.guruji.users.serviceimpl;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guruji.commons.entity.RoleEntity;
import com.guruji.commons.entity.UserEntity;
import com.guruji.users.enums.ErrorEnum;
import com.guruji.users.enums.ResponseEnum;
import com.guruji.users.exception.CustomException;
import com.guruji.users.service.RoleService;
import com.guruji.users.utils.BaseMethods;
import com.guruji.users.utils.RepositoryFactory;

/**
 * <h1>RoleServiceImpl</h1>
 * <p>
 * Mange user role CRUD
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final BaseMethods baseMethods;
	private final RepositoryFactory repositoryFactory;

	public RoleServiceImpl(BaseMethods baseMethods, RepositoryFactory repositoryFactory) {
		this.baseMethods = baseMethods;
		this.repositoryFactory = repositoryFactory;
	}

	@Override
	public ResponseEntity<Object> assignUserRole(Long id, String role) {
		String apiName = "assignUserRole";

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(BaseMethods.getUserName());
		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Optional<UserEntity> assigneeUserOptional = repositoryFactory.getUserEntityRepository().findById(id);
		if (!assigneeUserOptional.isPresent()) {
			throw new CustomException(ErrorEnum.ASSIGNEE_USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Optional<RoleEntity> roleOptional = repositoryFactory.getRoleEntityRepository().findByRoleName(role);
		if (!roleOptional.isPresent()) {
			throw new CustomException(ErrorEnum.ROLE_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		UserEntity assigneeUser = assigneeUserOptional.get();
		RoleEntity roleToBeAssign = roleOptional.get();
		Set<RoleEntity> roles = assigneeUser.getRoles();
		roles.add(roleToBeAssign);

		assigneeUser.setRoles(roles);
		repositoryFactory.getUserEntityRepository().save(assigneeUser);

		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(null, null, ResponseEnum.ROLE_ASSIGN_SUCCESSFUL.getValue()),
				HttpStatus.OK);
	}
}
