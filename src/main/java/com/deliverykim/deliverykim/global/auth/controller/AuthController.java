package com.deliverykim.deliverykim.global.auth.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverykim.deliverykim.global.auth.model.dto.Login;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.model.dto.WithdrawalDto;
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
	public CommonResponseEntity<SignUpDto.Response> signUp(@Valid @RequestBody SignUpDto.Request signupRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.signUp(signupRequest));
	}

	@GetMapping("/login")
	public CommonResponseEntity<Login.Response> login(@Valid @RequestBody Login.Request loginRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.login(loginRequest));
	}

	@DeleteMapping("/withdrawal")
	public CommonResponseEntity<WithdrawalDto.Response> withdrawal(@Valid @RequestBody WithdrawalDto.Request signupRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
	}

}
