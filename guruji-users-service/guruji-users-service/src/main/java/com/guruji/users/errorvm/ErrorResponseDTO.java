package com.guruji.users.errorvm;

public class ErrorResponseDTO {

	private String status;
	private String message;

	public ErrorResponseDTO(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErrorResponseDTO() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
