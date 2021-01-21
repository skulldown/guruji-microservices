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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <h1>UserEntity</h1>
 * <p>
 * This will use as mapping entity for application users
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 13-01-2021
 */
@Table(name = "guruji_users")
@Entity
public class UserEntity extends BaseEntityAudit {

	private static final long serialVersionUID = 1L;
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "email")
  private String email;

  @Column(name = "gender")
  private String gender;

  @Column(name = "contact_no")
  private String contactNo;

  @Column(name = "first_time_password_changed")
  private Boolean firstTimePasswordChanged;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "login_counter")
  private Integer loginCounter;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "account_non_expired")
  private Boolean accountNonExpired;

  @Column(name = "credentials_non_expired")
  private Boolean credentialsNonExpired;

  @Column(name = "account_non_locked")
  private Boolean accountNonLocked;

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "dob")
	private LocalDateTime dob;

  @Column(name = "birth_location")
  private String birthLocation;

  @Column(name = "interests")
  private String interests;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "roles", referencedColumnName = "role_name")})
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @BatchSize(size = 20)
  private Set<RoleEntity> roles = new HashSet<>();
  
  public UserEntity() {
  }

  public UserEntity(Long id) {
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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getLoginCounter() {
    return loginCounter;
  }

  public void setLoginCounter(Integer loginCounter) {
    this.loginCounter = loginCounter;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public Boolean getCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public Boolean getAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public Boolean getFirstTimePasswordChanged() {
    return firstTimePasswordChanged;
  }

  public void setFirstTimePasswordChanged(Boolean firstTimePasswordChanged) {
    this.firstTimePasswordChanged = firstTimePasswordChanged;
  }

  public Set<RoleEntity> getRoles() {
      return roles;
  }

  public void setRoles(Set<RoleEntity> roles) {
      this.roles = roles;
  }

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
