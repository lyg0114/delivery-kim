package com.deliverykim.deliverykim.global.exception;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseCode {

	//---------------------------------------- 공통 영역 ----------------------------------------

	SUCCESS("0000", "성공", HttpStatus.OK),
	CREATE_SUCCESS("0001", "성공", HttpStatus.CREATED),

	FAIL("1000", "실패", HttpStatus.BAD_REQUEST),
	UNKNOWN_ERROR("1001", "알수없는 오류", HttpStatus.INTERNAL_SERVER_ERROR),
	ILLEGAL_ARGUMENT("1002", "잘못된 파라메터 오류", HttpStatus.BAD_REQUEST),
	ILLEGAL_ARGUMENT_FORMAT("1003", "잘못된 파라메터 포맷 오류", HttpStatus.BAD_REQUEST),
	ILLEGAL_STATE("1004", "잘못된 상태 오류", HttpStatus.BAD_REQUEST),
	DB_ERROR("1005", "DB 오류", HttpStatus.INTERNAL_SERVER_ERROR),
	NO_CONTENT("1006", "정보 없음", HttpStatus.NO_CONTENT),
	NOT_SUPPORTED("1007", "지원되지 않는 기능 입니다.", HttpStatus.NOT_IMPLEMENTED),

	EXCHANGE_FALLBACK("9999", "내부 서비스 통신 오류.", HttpStatus.INTERNAL_SERVER_ERROR),

	//---------------------------------------- 도메인 영역 ----------------------------------------

	;

	private static final Map<String, ResponseCode> lookup = new HashMap<>();

	static {
		for (ResponseCode responseCode : ResponseCode.values()) {
			lookup.put(responseCode.getCode(), responseCode);
		}
	}

	private final String code;
	private final HttpStatus httpStatus;

	@Setter
	private String message;

	ResponseCode(String code, String message, HttpStatus httpStatus) {
		this.code = code;
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public static ResponseCode get(String code) {
		ResponseCode responseCode = lookup.get(code);
		if (null == responseCode) {
			throw new NullPointerException();
		}

		return responseCode;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatus;
	}

	private static final String RESULT_CODE = "Result-Code";
	private static final String RESULT_MESSAGE = "Result-Message";

	public HttpHeaders generateCommonResponseHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.put(RESULT_CODE, Collections.singletonList(code));
		httpHeaders.put(RESULT_MESSAGE, Collections.singletonList(URLEncoder.encode(message, StandardCharsets.UTF_8)));
		return httpHeaders;
	}

	public HttpHeaders generateCommonResponseHeaders(String message) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.put(RESULT_CODE, Collections.singletonList(code));
		httpHeaders.put(RESULT_MESSAGE, Collections.singletonList(URLEncoder.encode(message, StandardCharsets.UTF_8)));
		return httpHeaders;
	}

	public MultiValueMap<String, String> generateCommonResponseHeaders(
		MultiValueMap<String, String> headers) {
		headers.put(RESULT_CODE, Collections.singletonList(code));
		headers.put(RESULT_MESSAGE, Collections.singletonList(URLEncoder.encode(message, StandardCharsets.UTF_8)));
		return headers;
	}

}
