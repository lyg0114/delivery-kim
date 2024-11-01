package com.deliverykim.deliverykim.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.exception
 * @since : 01.11.24
 */
@Getter
@Setter
@ToString
@Slf4j
public class CommonResponseEntity<T> extends ResponseEntity<T> {

	public CommonResponseEntity(ResponseCode responseCode) {
		super(null, responseCode.generateCommonResponseHeaders(), responseCode.getHttpStatusCode());
	}

	public CommonResponseEntity(ResponseCode responseCode, @Nullable T body) {
		super(body, responseCode.generateCommonResponseHeaders(), responseCode.getHttpStatusCode());
		printResponseLog(responseCode);
	}

	public CommonResponseEntity(ResponseCode responseCode, Throwable ex) {
		super(null, responseCode.generateCommonResponseHeaders(), responseCode.getHttpStatusCode());
		printResponseLog(responseCode, ex);
	}

	private void printResponseLog(ResponseCode responseCode) {
		log.info("resultCode : {}, resultMessage : {}", responseCode.getCode(), responseCode.getMessage());
	}

	private void printResponseLog(ResponseCode responseCode, Throwable e) {
		log.error("resultCode : {}, resultMessage : {}, message : {}",
			responseCode.getCode(), responseCode.getMessage(), e.getMessage(), e);
	}

}
