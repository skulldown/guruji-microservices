package com.guruji.users.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guruji.commons.entity.RelationEntity;
import com.guruji.commons.entity.UserEntity;
import com.guruji.users.enums.ErrorEnum;
import com.guruji.users.enums.ResponseEnum;
import com.guruji.users.exception.CustomException;
import com.guruji.users.requestvm.RelationVM;
import com.guruji.users.service.RelationService;
import com.guruji.users.utils.BaseMethods;
import com.guruji.users.utils.RepositoryFactory;

/**
 * <h1>RelationServiceImpl</h1>
 * <p>
 * To manage user's relations
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 18-01-2021
 */
@Service
@Transactional
public class RelationServiceImpl implements RelationService {

	private final RepositoryFactory repositoryFactory;
	private final PasswordEncoder passwordEncoder;
	private final BaseMethods baseMethods;

	public RelationServiceImpl(RepositoryFactory repositoryFactory, PasswordEncoder passwordEncoder,
			BaseMethods baseMethods) {
		this.repositoryFactory = repositoryFactory;
		this.passwordEncoder = passwordEncoder;
		this.baseMethods = baseMethods;
	}

	@Override
	public ResponseEntity<Object> relationAdd(RelationVM relationVM) {

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(BaseMethods.getUserName());
		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		insertRelation(relationVM, userOptional.get());
		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(null, null, ResponseEnum.RELATION_ADD_SUCCESSFUL.getValue()),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> allRelation() {

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(BaseMethods.getUserName());
		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserEntity gurujiUser = userOptional.get();
		
		List<RelationEntity> relationEntityOptional = repositoryFactory.getRelationEntityRepository()
				.findByGurujiUserAndStatusTrue(gurujiUser.getId());
		
		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(relationEntityOptional, null, null),
				HttpStatus.OK);
	}
	
	public void insertRelation(RelationVM relationVM, UserEntity mainUser) {

		RelationEntity relationDetail = new RelationEntity();

		UserEntity user = mainUser;
		Set<UserEntity> userEntities = new HashSet<>(Arrays.asList(user));

		relationDetail.setFirstName(relationVM.getfName());
		relationDetail.setMiddleName(relationVM.getmName());
		relationDetail.setLastName(relationVM.getlName());
		relationDetail.setDob(relationVM.getDob());
		relationDetail.setBirthLocation(relationVM.getBirthLocation());
		relationDetail.setGender(relationVM.getGender());
		relationDetail.setRelation(relationVM.getRelation());
		relationDetail.setRelativeUser(userEntities);
		relationDetail.setGurujiUser(mainUser.getId());
		relationDetail.setCreatedBy(mainUser);
		relationDetail.setUpdatedBy(mainUser);
		relationDetail.setCreatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));
		relationDetail.setUpdatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));
		relationDetail.setStatus(true);

		repositoryFactory.getRelationEntityRepository().save(relationDetail);
	}

	@Override
	public ResponseEntity<Object> relationUpdate(RelationVM relationVM) {

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(BaseMethods.getUserName());
		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		updateRelation(relationVM, userOptional.get());

		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(null, null, ResponseEnum.RELATION_UPDATE_SUCCESSFUL.getValue()),
				HttpStatus.OK);
	}

	public void updateRelation(RelationVM relationVM, UserEntity gurujiUser) {

		Optional<RelationEntity> relationEntityOptional = repositoryFactory.getRelationEntityRepository()
				.findByIdAndGurujiUserAndStatusTrue(relationVM.getId(),gurujiUser.getId());

		if (!relationEntityOptional.isPresent()) {
			throw new CustomException(ErrorEnum.RELATION_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		RelationEntity relationDetail = relationEntityOptional.get();

		relationDetail.setFirstName(relationVM.getfName());
		relationDetail.setMiddleName(relationVM.getmName());
		relationDetail.setLastName(relationVM.getlName());
		relationDetail.setDob(relationVM.getDob());
		relationDetail.setBirthLocation(relationVM.getBirthLocation());
		relationDetail.setGender(relationVM.getGender());
		relationDetail.setRelation(relationVM.getRelation());
		relationDetail.setUpdatedBy(gurujiUser);
		relationDetail.setUpdatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));

		repositoryFactory.getRelationEntityRepository().save(relationDetail);
	}
	
	@Override
	public ResponseEntity<Object> relationDelete(Long realtionId) {

		Optional<UserEntity> userOptional = repositoryFactory.getUserEntityRepository()
				.findByEmail(BaseMethods.getUserName());
		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorEnum.USER_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserEntity gurujiUser = userOptional.get();
		
		Optional<RelationEntity> relationEntityOptional = repositoryFactory.getRelationEntityRepository()
				.findByIdAndGurujiUserAndStatusTrue(realtionId,gurujiUser.getId());

		if (!relationEntityOptional.isPresent()) {
			throw new CustomException(ErrorEnum.RELATION_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		RelationEntity relationDetail = relationEntityOptional.get();
		relationDetail.setUpdatedBy(gurujiUser);
		relationDetail.setUpdatedDate(BaseMethods.convertToLocalDateTimeViaInstant(BaseMethods.getDate()));
		relationDetail.setStatus(false);;

		repositoryFactory.getRelationEntityRepository().save(relationDetail);
		
		return new ResponseEntity<>(
				baseMethods.modifyResponseObject(null, null, ResponseEnum.RELATION_DELETE_SUCCESSFUL.getValue()),
				HttpStatus.OK);
	}
}
