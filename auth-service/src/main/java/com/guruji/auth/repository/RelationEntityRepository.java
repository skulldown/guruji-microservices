package com.guruji.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guruji.commons.entity.RelationEntity;

@Repository
public interface RelationEntityRepository extends JpaRepository<RelationEntity, Long> {
 
	Optional<RelationEntity> findByIdAndGurujiUserAndStatusTrue(Long id,Long gurujiUser);
	
	List<RelationEntity> findByGurujiUserAndStatusTrue(Long gurujiUser);
	
}