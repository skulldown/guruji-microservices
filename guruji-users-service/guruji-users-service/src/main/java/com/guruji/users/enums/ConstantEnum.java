package com.guruji.users.enums;

public enum ConstantEnum {

	USERNAME("userName"), STATUS("status"),;

	private String value;

	ConstantEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
