package com.deliverykim.deliverykim.global.auth.controller;

import com.deliverykim.deliverykim.global.auth.model.dto.LoginDto;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.model.dto.WithdrawalDto;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.exception.CommonResponseEntity;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/sign-up")
	public CommonResponseEntity<SignUpDto.Response> signUp(@Valid @RequestBody SignUpDto.Request signupRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.signUp(signupRequest));
	}

	@GetMapping("/login")
	public CommonResponseEntity<LoginDto.Response> login(@Valid @RequestBody LoginDto.Request loginRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.login(loginRequest));
	}

	@DeleteMapping("/withdrawal")
	public CommonResponseEntity<WithdrawalDto.Response> withdrawal(@Valid @RequestBody WithdrawalDto.Request withdrawalRequest) {
		return new CommonResponseEntity<>(ResponseCode.SUCCESS, authService.withdrawal(withdrawalRequest));
	}

}
