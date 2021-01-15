package com.guruji.auth.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.guruji.auth.enums.ResponseEnum;

/**
 * <h1>ResponseUtil</h1>
 * <p>This is helper to send custom response from filter</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 29-05-2020
 */
public class ResponseUtil {

  private ResponseUtil() {
    // private constructor
  }

  /**
   * <p>
   * This will be used to make custom response with message provided
   * </p>
   *
   * @param req        ServletRequest
   * @param message    message
   * @param httpStatus HttpStatus
   * @return Map<String, Object>
   */
  public static Map<String, Object> getErrorDetails(ServletRequest req, String message, HttpStatus httpStatus) {
    Map<String, Object> errorDetails = new HashMap<>();
    errorDetails.put(ResponseEnum.STATUS.getValue(), httpStatus.value());
    errorDetails.put(ResponseEnum.MESSAGE.getValue(), message);

    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put(ResponseEnum.TIME_STAMP.getValue(), new Date().getTime());
    dataMap.put(ResponseEnum.STATUS.getValue(), httpStatus.value());
    dataMap.put(ResponseEnum.ERROR.getValue(), message);
    dataMap.put(ResponseEnum.MESSAGE.getValue(), httpStatus.name());
    dataMap.put(ResponseEnum.PATH.getValue(), ((HttpServletRequest) req).getServletPath());

    errorDetails.put(ResponseEnum.DATA.getValue(), dataMap);
    return errorDetails;
  }

  /**
   * <p>
   * This will be used to make custom response with message provided
   * </p>
   *
   * @param req        ServletRequest
   * @param message    message
   * @param error      error
   * @param httpStatus HttpStatus
   * @return Map<String, Object>
   */
  public static Map<String, Object> getErrorDetailsWithMessageAndError(ServletRequest req, String message, String error,
    HttpStatus httpStatus) {
    Map<String, Object> errorDetails = ResponseUtil.getErrorDetails(req, error, httpStatus);
    errorDetails.put(ResponseEnum.MESSAGE.getValue(), message);
    return errorDetails;
  }
}
