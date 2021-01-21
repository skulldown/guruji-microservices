package com.guruji.users.enums;

public enum ResponseEnum {

	REGISTRATION_SUCCESSFUL("Registration successful"), ROLE_ASSIGN_SUCCESSFUL("Role assigned successfully"),
	RELATION_ADD_SUCCESSFUL("Relation added successfully"),RELATION_UPDATE_SUCCESSFUL("Relation updated successfully"),
	RELATION_DELETE_SUCCESSFUL("Relation removed successfully"),;

	private String value;

	ResponseEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
