package com.guruji.users.requestvm;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guruji.users.utils.CustomDateDeserializer;

/**
 * View Model object for relation information request.
 */
public class RelationVM implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "First nane is required")
	@NotBlank(message = "First nane is required")
	private String fName;

	@NotEmpty(message = "Middle nane is required")
	@NotBlank(message = "Middle nane is required")
	private String mName;

	@NotEmpty(message = "Last nane is required")
	@NotBlank(message = "Last nane is required")
	private String lName;

	private LocalDateTime dob;

	private String birthLocation;
	private String gender;
	private String relation;

	public RelationVM() {
	}

	public RelationVM(
			Long id,
			@NotEmpty(message = "First nane is required") @NotBlank(message = "First nane is required") String fName,
			@NotEmpty(message = "Middle nane is required") @NotBlank(message = "Middle nane is required") String mName,
			@NotEmpty(message = "Last nane is required") @NotBlank(message = "Last nane is required") String lName,
			LocalDateTime dob, String birthLocation, String gender, String relation) {
		super();
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.dob = dob;
		this.birthLocation = birthLocation;
		this.gender = gender;
		this.relation = relation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	@JsonDeserialize(using = CustomDateDeserializer.class)
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public String getBirthLocation() {
		return birthLocation;
	}

	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RelationVM [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
				+ ", birthLocation=" + birthLocation + ", gender=" + gender + ", relation=" + relation + "]";
	}
}
