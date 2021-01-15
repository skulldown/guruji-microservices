package com.guruji.auth.implementation;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import org.slf4j.MDC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guruji.auth.enums.GeneralEnum;
import com.guruji.auth.exception.SecurityException;
import com.guruji.auth.utils.ResponseUtil;

import io.jsonwebtoken.JwtException;

/**
 * <h1>JwtTokenFilter</h1>
 * <p>This filter will be used to authenticate user on every request</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 13-05-2020
 */
public class JwtTokenFilter extends GenericFilterBean {

  private JwtTokenProvider jwtTokenProvider;

  private ObjectMapper objectMapper;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
    throws IOException, ServletException {
	
	HttpServletRequest rewq = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
    
    boolean checkStatus = false;
	
	if (!rewq.getServletPath().equals("actuator/logfile") && !rewq.getServletPath().equals("/actuator/info")
			&& !rewq.getServletPath().equals("/actuator/health") && !rewq.getServletPath().equals("/actuator/env")) {
		checkStatus = true;
	}
	
    if ((token != null && !token.isEmpty()) && checkStatus) {
      try {
        jwtTokenProvider.validateToken(token);
        Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
        //setting auth in the context
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (DisabledException e) {
        // response status
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        int statusValue = status.value();
        response.setStatus(statusValue);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), ResponseUtil.getErrorDetails(req, e.getMessage(), status));
        throw new SecurityException(e.getMessage(), HttpStatus.UNAUTHORIZED);
      } catch (JwtException | IllegalArgumentException e) {
        // response status
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        int statusValue = status.value();
        response.setStatus(statusValue);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(),
          ResponseUtil.getErrorDetails(req, "Invalid JWT token", status));
        throw new SecurityException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
      }

    }
    if (token != null && !token.isEmpty()) {
      String newToken = jwtTokenProvider.createNewToken(token);
      setHeader(response, newToken);

    }
    filterChain.doFilter(req, res);

  }
  
  private void setHeader (HttpServletResponse response,String newToken) {
		response.setHeader(GeneralEnum.AUTHORIZATION.getValue(), newToken);
		response.setHeader(GeneralEnum.TRACE_ID.getValue(), MDC.get("traceId"));
		response.setHeader(GeneralEnum.ACCESS_CONTROL_EXPOSE_HEADERS.getValue(), "Authorization");
	}
}
