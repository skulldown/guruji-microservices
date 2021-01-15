package com.guruji.commons.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1>RoleEntity</h1>
 * <p>
 * This will use as mapping entity for roles
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 13-01-2021
 */
@Table(name = "roles")
@Entity
public class RoleEntity extends BaseEntityAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "deactivate")
	private Boolean deactivate;

	public RoleEntity() {
	}

	public RoleEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getDeactivate() {
		return deactivate;
	}

	public void setDeactivate(Boolean deactivate) {
		this.deactivate = deactivate;
	}

}
