package com.guruji.auth.enums;

/**
 * <h1>ExceptionEnum</h1>
 * <p>This enum will be used to return error api response messages</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 13-05-2020
 */
public enum ExceptionEnum {

  SOMETHING_WENT_WRONG("Something went wrong"),
  ;

  private String value;

  private String message;

  ExceptionEnum(String value) {
    this.value = value;
  }

  ExceptionEnum(String message, String value) {
    this.message = message;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String getMessage() {
    return message;
  }
}
