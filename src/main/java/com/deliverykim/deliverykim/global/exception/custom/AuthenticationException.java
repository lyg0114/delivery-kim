package com.deliverykim.deliverykim.global.exception.custom;


import com.deliverykim.deliverykim.global.exception.ResponseCode;

/**
 * CREATED by 장해솔 on 2024/02/23
 */
public class AuthenticationException extends UserHandlerException {
    public AuthenticationException(ResponseCode code) {
        super(code);
    }

    public AuthenticationException(ResponseCode code, Throwable cause) {
        super(code, cause);
    }
}
