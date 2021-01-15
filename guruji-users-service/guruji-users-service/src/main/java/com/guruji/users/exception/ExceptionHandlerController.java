package com.guruji.users.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.guruji.users.enums.ErrorEnum;
import com.guruji.users.errorvm.ErrorResponseDTO;


/**
 * This class is designed to handle all type of rest exception at one place.
 * 
 * @author
 *
 */
@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(CustomException.class)
	public ErrorResponseDTO handleCustomException(HttpServletResponse res, CustomException e) throws IOException {
		res.setStatus(e.getHttpStatus().value());
		return new ErrorResponseDTO(ErrorEnum.ERROR.getErrorMessage(), 
				e.getError().getErrorMessage());
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponseDTO handleException(HttpServletResponse res, Exception e) throws IOException {
		e.printStackTrace();
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ErrorResponseDTO(ErrorEnum.ERROR.getErrorMessage(), ErrorEnum.SOMETHING_WENT_WRONG.getErrorMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException(HttpServletResponse res, IllegalArgumentException e) throws IOException {
		res.sendError(HttpStatus.BAD_REQUEST.value(), ErrorEnum.SOMETHING_WENT_WRONG.getErrorMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public void handleMissingServletRequestParameterException(HttpServletResponse res, Exception e) throws IOException {
		res.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public void handleHttpRequestMethodNotSupportedException(HttpServletResponse res, Exception e) throws IOException {
		res.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
	}
}
