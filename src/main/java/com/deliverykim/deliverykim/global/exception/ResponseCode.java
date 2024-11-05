package com.deliverykim.deliverykim.global.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

	//---------------------------------------- 회원 도메인 영역 ( 2000 ~ 2999 ) ----------------------------------------
	ALREADY_EXIST_EMAIL("2000", "이미 존재하는 계정", HttpStatus.BAD_REQUEST),
	DO_NOT_EXIST_EMAIL("2001", "존재하지 않는 계정", HttpStatus.BAD_REQUEST),
	DO_NOT_MATCH_PASSWORLD("2002", "패스워드 불일치", HttpStatus.BAD_REQUEST),
	TOKEN_AUTHENTICATION_FAIL("2003", "토큰 인증 실패", HttpStatus.UNAUTHORIZED),
	TOKEN_EXPIRED_ERROR("2004","토큰 기한 만료",HttpStatus.UNAUTHORIZED),
	REFRESH_TOKEN_AUTHENTICATION_FAIL("2005", "리프레쉬 토큰 인증 실패", HttpStatus.UNAUTHORIZED),
	INVALID_TOKEN("2007", "유효 하지 않은 토큰", HttpStatus.UNAUTHORIZED),
	WITHDRAWAL_EMAIL("2009", "이미 탈퇴처리된 계정", HttpStatus.UNAUTHORIZED),
	TOKEN_NOT_AUTHORIZED("2010", "유효하지 않은 권한", HttpStatus.UNAUTHORIZED),

	//---------------------------------------- 스토어 도메인 영역 ( 3000 ~ 3999 ) ----------------------------------------
	STORE_COUNT_LIMIT_EXCEEDED("3000", "가게주인당 스토어는 최대 3개까지 허용", HttpStatus.BAD_REQUEST),


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

	public static ResponseCode getResponseCode(String code) {
		return Arrays.stream(ResponseCode.values())
				.filter(val -> val.hasCodeName(code))
				.findAny()
				.orElse(null);
	}

	public boolean hasCodeName(String code) {
		return this.code.equals(code);
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatus;
	}

	public static final String RESULT_CODE = "Result-Code";
	public static final String RESULT_MESSAGE = "Result-Message";

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
