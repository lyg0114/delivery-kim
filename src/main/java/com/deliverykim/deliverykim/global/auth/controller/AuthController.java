package com.deliverykim.deliverykim.global.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.exception.CommonResponseEntity;
import com.deliverykim.deliverykim.global.exception.ResponseCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.auth.controller
 * @since : 02.11.24
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public CommonResponseEntity<SignUpDto.Response> register(@Valid @RequestBody SignUpDto.Request signupRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.register(signupRequest));
	}

}
