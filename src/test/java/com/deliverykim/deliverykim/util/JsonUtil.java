package com.deliverykim.deliverykim.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.util
 * @since : 03.11.24
 */
public class JsonUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String readJsonStrFromFile(String filePath) {
		File resource = null;
		byte[] byteArray = null;
		try {
			resource = new ClassPathResource(filePath).getFile();
			byteArray = Files.readAllBytes(resource.toPath());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return new String(byteArray);
	}

	public static <T> T converObjFromJsonStr(String jsonString, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonString, valueType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String converStrFromObj(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert object to JSON string", e);
		}
	}

	public static <T> T fromJsonFile(String filePath, Class<T> valueType) {
		File resource = null;
		byte[] byteArray = null;
		String jsonStr = null;

		try {
			resource = new ClassPathResource(filePath).getFile();
			byteArray = Files.readAllBytes(resource.toPath());
			jsonStr = new String(byteArray);

			return objectMapper.readValue(jsonStr, valueType);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
