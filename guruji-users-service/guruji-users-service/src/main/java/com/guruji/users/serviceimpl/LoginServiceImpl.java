package com.guruji.users.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guruji.auth.enums.GeneralEnum;
import com.guruji.auth.implementation.JwtTokenProvider;
import com.guruji.auth.repository.UserEntityRepository;
import com.guruji.commons.entity.RoleEntity;
import com.guruji.commons.entity.UserEntity;
import com.guruji.users.enums.ErrorEnum;
import com.guruji.users.exception.CustomException;
import com.guruji.users.requestvm.LoginVM;
import com.guruji.users.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	private final JwtTokenProvider tokenProvider;

	private final UserEntityRepository userEntityRepository;

	private final PasswordEncoder passwordEncoder;

	public LoginServiceImpl(JwtTokenProvider tokenProvider, UserEntityRepository userEntityRepository,
			PasswordEncoder passwordEncoder) {
		this.tokenProvider = tokenProvider;
		this.userEntityRepository = userEntityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<JWTToken> authorizeUser(LoginVM loginVM) {

		Optional<UserEntity> userOptional = userEntityRepository.findByEmail(loginVM.getUsername());

		System.out.println("userOptional.isPresent() ::: " + userOptional.isPresent());
		if (!userOptional.isPresent()
				|| !this.passwordEncoder.matches(loginVM.getPassword(), userOptional.get().getPassword())) {
			throw new CustomException(ErrorEnum.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
		}

		System.out.println("TEST ! : " + userOptional.get().getRoles().size());
		List<String> roleList = new ArrayList<String>();
		for (RoleEntity userRole : userOptional.get().getRoles()) {
			System.out.println("userRole.getRoleName() :: " + userRole.getRoleName());
			roleList.add(userRole.getRoleName());
		}

		System.out.println("TEST 2");
		String jwt = tokenProvider.createToken(loginVM.getUsername(), roleList);

		System.out.println("TEST 3" + jwt);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(GeneralEnum.AUTHORIZATION.getValue(), "Bearer " + jwt);

		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);

	}

	/**
	 * <b> This class holds static token for user.</b>
	 * 
	 * @author Yogesh Makwana
	 *
	 */
	static class JWTToken {

		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}

}
