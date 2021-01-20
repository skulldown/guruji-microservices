package com.guruji.users.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.guruji.users.response.model.Response;
import com.guruji.users.response.model.ResponseMetadata;

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

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public Object modifyResponseObject(Object obj, ResponseMetadata responseMetadata, String message) {
		if (obj instanceof ArrayList<?>) {
			if (((ArrayList<?>) obj).size() == 1) {
				obj = ((ArrayList<?>) obj).get(0);
			} else if (!checkIfListEmptyOrNot((List<?>) obj)) {
				obj = new HashMap<>();
			}
		} else if (obj == null) {
			obj = new HashMap<>();
		}
		Response response = new Response();
		response.setResults(obj);
		response.setMessage(message != null && !message.isEmpty() ? message : null);
		response.setMetadata(responseMetadata);
		return response;
	}

}
