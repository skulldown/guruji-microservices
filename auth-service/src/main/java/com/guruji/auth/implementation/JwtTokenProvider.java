package com.guruji.auth.implementation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.guruji.auth.enums.GeneralEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private static final Long VALIDITY_SEC = 900000L;
	private final UserDetailsService userDetailsService;

	public JwtTokenProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public String createToken(String username, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + VALIDITY_SEC);

		return Jwts.builder().setSubject(username).claim(GeneralEnum.AUTH.getValue(), roles)
				.signWith(SignatureAlgorithm.HS256, GeneralEnum.SECRET.getValue()).setExpiration(validity).compact();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(GeneralEnum.AUTHORIZATION.getValue());
		Cookie[] cookielist = req.getCookies();

		if (bearerToken != null && bearerToken.startsWith(GeneralEnum.BEARER.getValue())) {
			return bearerToken.substring(7, bearerToken.length());
		}
		if (bearerToken != null) {
			return bearerToken;
		}
		if (cookielist != null) {
			for (Cookie cookie : cookielist) {
				if (cookie.getName() != null && cookie.getName().equals(GeneralEnum.ACCESS_TOKEN)
						&& cookie.getValue() != null && !cookie.getValue().isEmpty()) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
		Jwts.parser().setSigningKey(GeneralEnum.SECRET.getValue()).parseClaimsJws(token);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<String> getRoleList(String token) {
		return (List<String>) Jwts.parser().setSigningKey(GeneralEnum.SECRET.getValue()).parseClaimsJws(token).getBody()
				.get(GeneralEnum.AUTH.getValue());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(GeneralEnum.SECRET.getValue()).parseClaimsJws(token).getBody().getSubject();
	}

	public Authentication getAuthentication(String token) {
		// using data base: uncomment when you want to fetch data from data base
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		// from token take user value. comment below line for changing it taking from
		// data base
		return new UsernamePasswordAuthenticationToken(userDetails, GeneralEnum.EMPTY_STRING.getValue(),
				userDetails.getAuthorities());
	}

	public Date getIssuedAt(String token) {
		return Jwts.parser().setSigningKey(GeneralEnum.SECRET.getValue()).parseClaimsJws(token).getBody().getIssuedAt();
	}

	public String createNewToken(String token) {

		List<String> roleList = getRoleList(token);
		String username = getUsername(token);
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(GeneralEnum.AUTH.getValue(), roleList);
		Date now = new Date();
		Date validity = new Date(now.getTime() + VALIDITY_SEC);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, GeneralEnum.SECRET.getValue())//
				.compact();
	}
}
