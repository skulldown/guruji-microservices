package com.guruji.users.exception;

import org.springframework.http.HttpStatus;

import com.guruji.users.enums.ErrorEnum;


public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final ErrorEnum error;
    private final HttpStatus httpStatus;

    public CustomException(ErrorEnum error, HttpStatus httpStatus) {
    	this.error = error;
        this.httpStatus = httpStatus;
    }

    public ErrorEnum getError() {
        return error;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
