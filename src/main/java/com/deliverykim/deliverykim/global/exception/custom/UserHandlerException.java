package com.deliverykim.deliverykim.global.exception.custom;

import com.deliverykim.deliverykim.global.exception.ResponseCode;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.exception.custom
 * @since : 01.11.24
 */
public class UserHandlerException extends RuntimeException {

	private final ResponseCode code;

	public UserHandlerException(ResponseCode code) {
		super(code.getMessage());
		this.code = code;
	}

	public UserHandlerException(ResponseCode code, String message) {
		super(message);
		this.code = code;
	}

	public UserHandlerException(ResponseCode code, Throwable cause) {
		super(code.getMessage(), cause);
		this.code = code;
	}

	public ResponseCode getResponseCode() {
		return code;
	}
}
