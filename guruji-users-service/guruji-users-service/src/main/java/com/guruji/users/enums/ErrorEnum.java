package com.guruji.users.enums;

public enum ErrorEnum {

	METHOD_LOGIC_ERROR("An error occurred while executing the Logic"), SOMETHING_WENT_WRONG("Something went wron!"),
	USER_NOT_FOUND("User not found!"), ERROR("Error"), INVALID_CREDENTIALS("invalid username or password");

	private final String errorMessage;

	ErrorEnum(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
