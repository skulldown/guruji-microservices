package com.guruji.auth.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.guruji.auth.repository.UserEntityRepository;
import com.guruji.commons.entity.RoleEntity;
import com.guruji.commons.entity.UserEntity;

@org.springframework.stereotype.Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	private final UserEntityRepository userEntityRepository;

	public MyUserDetailsService(UserEntityRepository userEntityRepository) {
		this.userEntityRepository = userEntityRepository;
	}

	private static org.springframework.security.core.userdetails.User buildUserForAuthentication(UserEntity userEntity,
			List<GrantedAuthority> authorities) {

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
				authorities);
	}

	private static List<GrantedAuthority> buildUserAuthority(List<String> authorityList) {

		Set<GrantedAuthority> setAuthSet = new HashSet<>();

		for (String authority : authorityList) {
			setAuthSet.add(new SimpleGrantedAuthority("ROLE_" + authority));
		}

		return new ArrayList<>(setAuthSet);
	}

	@Transactional
	public UserDetails loadUserByUsername(final String username) {

		Optional<UserEntity> userEntityOptional = userEntityRepository.findByEmail(username);

		if (!userEntityOptional.isPresent()) {
			throw new DisabledException("User Not Found");
		}
		
		if (Boolean.FALSE.equals(userEntityOptional.get().getEnabled())) {
			throw new DisabledException("User is Disabled");
		}

		List<String> authorityList = new ArrayList<>();
		for (RoleEntity roleEntity : userEntityOptional.get().getRoles()) {
			authorityList.add(roleEntity.getRoleName());
		}

		List<GrantedAuthority> authorities = buildUserAuthority(authorityList);
		return buildUserForAuthentication(userEntityOptional.get(), authorities);

	}

}