package com.guruji.users.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * <h1>CustomDateDeserializer</h1>
 * <p>
 * Used to convert date to specific date format
 * </p>
 *
 * @author Yogesh Makwana
 * @version 1.0
 * @since 13-01-2021
 */
@Component
public class CustomDateDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");

	public CustomDateDeserializer() {
		this(null);
	}

	public CustomDateDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String date = jsonparser.getText();
		try {
			return formatter.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}