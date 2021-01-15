package com.guruji.auth.enums;

/**
 * <h1>ResponseEnum</h1>
 * <p>This is enum values to be used in sending response in filter</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 29-05-2020
 */
public enum ResponseEnum {

  STATUS("status"),
  MESSAGE("message"),
  TIME_STAMP("timestamp"),
  ERROR("error"),
  PATH("path"),
  DATA("data"),
  ;

  private String value;

  ResponseEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
