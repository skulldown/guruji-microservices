package com.guruji.users.enums;

public enum ErrorEnum {

	METHOD_LOGIC_ERROR("An error occurred while executing the Logic"), SOMETHING_WENT_WRONG("Something went wrong!"),
	USER_NOT_FOUND("User not found!"), ERROR("Error"), INVALID_CREDENTIALS("invalid username or password"),
	INVALID_MAIL("Please provide valid email id"),USER_ALREADY_EXIST("User already exists with given email id"),
	ROLE_NOT_FOUND("Role not found"), ASSIGNEE_USER_NOT_FOUND("Asignee user not found"),
	RELATION_NOT_FOUND("Relation not found"),;

	private final String errorMessage;

	ErrorEnum(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
