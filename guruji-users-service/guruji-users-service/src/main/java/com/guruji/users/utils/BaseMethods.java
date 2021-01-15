package com.guruji.users.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BaseMethods {

	public static String getUserName() {

		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		} else {
			return null;
		}
	}

	public boolean checkIfListEmptyOrNot(List<?> checkList) {
		return checkList != null && !checkList.isEmpty();
	}

	public static Date getDate() {
		try {
			return new Date();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Description : validate that given string is valid email or not
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String getFormattedDate() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		return dateFormatter.format(getDate());
	}

	public boolean checkIfStringEmptyOrNot(String string) {
		return string != null && !string.trim().isEmpty();
	}

}
