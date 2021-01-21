package com.guruji.users.requestvm;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guruji.users.utils.CustomDateDeserializer;

/**
 * View Model object for signup request.
 */
public class SignupVM implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "First nane is required")
	@NotBlank(message = "First nane is required")
	private String fName;

	@NotEmpty
	@NotBlank
	@Size(min = 1, max = 50)
	private String mName;

	@NotEmpty
	@NotBlank
	@Size(min = 1, max = 50)
	private String lName;

	@NotEmpty
	@NotBlank
	@Email(message = "Email is invalid")
	@Size(min = 1, max = 50)
	private String emailId;

	@NotEmpty
	@NotBlank
	@Size(min = 1, max = 50)
	private String password;

	@NotEmpty
	@NotBlank
	private String phoneNumber;

	@NotEmpty
	@NotBlank
	private String countryCode;

	private LocalDateTime dob;
	
	private String birthLocation;
	private String gender;
	private String interests;

	public SignupVM() {
	}

	public SignupVM(
			@NotEmpty(message = "First nane is required") @NotBlank(message = "First nane is required") String fName,
			@NotEmpty @NotBlank @Size(min = 1, max = 50) String mName,
			@NotEmpty @NotBlank @Size(min = 1, max = 50) String lName,
			@NotEmpty @NotBlank @Email(message = "Email is invalid") @Size(min = 1, max = 50) String emailId,
			@NotEmpty @NotBlank @Size(min = 1, max = 50) String password, @NotEmpty @NotBlank String phoneNumber,
			@NotEmpty @NotBlank String countryCode, LocalDateTime dob, String birthLocation, String gender,
			String interests) {
		super();
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.emailId = emailId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.countryCode = countryCode;
		this.dob = dob;
		this.birthLocation = birthLocation;
		this.gender = gender;
		this.interests = interests;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "SignupVM [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", emailId=" + emailId
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", countryCode=" + countryCode + ", dob="
				+ dob + ", birthLocation=" + birthLocation + ", gender=" + gender + ", interests=" + interests + "]";
	}

}
