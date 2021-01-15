package com.guruji.auth.implementation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.guruji.auth.enums.GeneralEnum;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

  private static final long serialVersionUID = 1L;

  private final String code;
  private final String token;
  private final String deviceos;
  private final String browser;
  private final String ip;

  public CustomWebAuthenticationDetails(HttpServletRequest request) {
    super(request);
    this.code = request.getParameter(GeneralEnum.CODE.getValue());
    this.token = request.getParameter(GeneralEnum.TOKEN.getValue());
    this.deviceos = request.getParameter(GeneralEnum.DEVICEOS.getValue());
    this.browser = request.getParameter(GeneralEnum.BROWSER.getValue());
    this.ip = request.getRemoteAddr();
  }

  public String getCode() {
    return code;
  }

  public String getToken() {
    return token;
  }

  public String getIp() {
    return ip;
  }

  public String getDeviceos() {
    return deviceos;
  }

  public String getBrowser() {
    return browser;
  }
}