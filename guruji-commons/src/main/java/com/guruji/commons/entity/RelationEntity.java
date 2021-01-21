package com.guruji.commons.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <h1>RelationEntity</h1>
 * <p>
 * This will use as mapping entity for application users's relations
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 18-01-2021
 */
@Table(name = "guruji_users_relation")
@Entity
public class RelationEntity extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private LocalDateTime dob;

	@Column(name = "birth_location")
	private String birthLocation;

	@Column(name = "relation")
	private String relation;
	
	private Boolean status;
	
	private Long gurujiUser;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_relations", joinColumns = {
			@JoinColumn(name = "user_relation_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<UserEntity> relativeUser = new HashSet<>();

	public RelationEntity() {
	}

	public RelationEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getGurujiUser() {
		return gurujiUser;
	}

	public void setGurujiUser(Long gurujiUser) {
		this.gurujiUser = gurujiUser;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<UserEntity> getRelativeUser() {
		return relativeUser;
	}

	public void setRelativeUser(Set<UserEntity> relativeUser) {
		this.relativeUser = relativeUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RelationEntity [id:" + id + ", firstName:" + firstName + ", middleName:" + middleName + ", lastName:"
				+ lastName + ", gender:" + gender + ", dob:" + dob + ", birthLocation:" + birthLocation + ", relation:"
				+ relation + ", status:" + status + ", gurujiUser:" + gurujiUser + ", relativeUser:" + relativeUser
				+ "]";
	}

	

}
