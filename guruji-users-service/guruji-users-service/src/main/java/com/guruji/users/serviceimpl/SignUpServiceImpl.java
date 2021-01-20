package com.guruji.users.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guruji.commons.entity.RoleEntity;
import com.guruji.commons.entity.UserEntity;
import com.guruji.users.enums.ErrorEnum;
import com.guruji.users.enums.ResponseEnum;
import com.guruji.users.exception.CustomException;
import com.guruji.users.requestvm.SignupVM;
import com.guruji.users.service.SignUpService;
import com.guruji.users.utils.BaseMethods;
import com.guruji.users.utils.RepositoryFactory;

/**
 * <h1>SignUpServiceImpl</h1>
 * <p>
 * To register user
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 15-01-2021
 */
@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

	private final RepositoryFactory repositoryFactory;
	private final PasswordEncoder passwordEncoder;
	private final BaseMethods baseMethods;

	public SignUpServiceImpl(RepositoryFactory repositoryFactory, PasswordEncoder passwordEncoder,
			BaseMethods baseMethods) {
		this.repositoryFactory = repositoryFactory;
		this.passwordEncoder = passwordEncoder;
		this.baseMethods = baseMethods;
	}

	@Override
	public ResponseEntity<Object> signUp(SignupVM signupVM) {

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(signupVM.getEmailId());
		if (userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
		}

		insertUser(signupVM);
		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(null, null, ResponseEnum.REGISTRATION_SUCCESSFUL.getValue()),
				HttpStatus.OK);
	}

	public void insertUser(SignupVM signupVM) {

		UserEntity user = new UserEntity();
		RoleEntity defultRole = new RoleEntity(2l);
		defultRole.setRoleName("user");
		Set<RoleEntity> roles = new HashSet<>(Arrays.asList(defultRole));

		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setContactNo(signupVM.getPhoneNumber());
		user.setCreatedBy(new UserEntity(1l));
		user.setUpdatedBy(new UserEntity(1l));
		user.setCreatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));
		user.setCredentialsNonExpired(true);
		user.setEmail(signupVM.getEmailId());
		user.setEnabled(true);
		user.setFirstName(signupVM.getfName());
		user.setMiddleName(signupVM.getmName());
		user.setLastName(signupVM.getlName());
		user.setFirstTimePasswordChanged(false);
		user.setGender(signupVM.getGender());
		user.setLoginCounter(0);
		user.setPassword(passwordEncoder.encode(signupVM.getPassword()));
		user.setUpdatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));
		user.setCountryCode(signupVM.getCountryCode());
		user.setDob(signupVM.getDob());
		user.setBirthLocation(signupVM.getBirthLocation());
		user.setInterests(signupVM.getInterests());
		user.setRoles(roles);

		repositoryFactory.getUserEntityRepository().save(user);
	}
}
