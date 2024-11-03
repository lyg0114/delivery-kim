package com.deliverykim.deliverykim.unit.global.auth.controller;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.global.auth.model.dto.LoginDto;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.model.dto.TokenInfo;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import com.deliverykim.deliverykim.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.controller
 * @since : 2024. 11. 2.
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@MockBean AuthService authService;
	@MockBean TokenManager tokenManager;


	@DisplayName("회원가입 : 정상 회원가입")
	@Test
	void sign_up_test_1() throws Exception {
		// given
		String signUpSuccess = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/signup/success_signup.json");
		SignUpDto.Request signupRequest = JsonUtil.converObjFromJsonStr(signUpSuccess, SignUpDto.Request.class);

		SignUpDto.Response response = SignUpDto.Response.builder()
			.email(signupRequest.getEmail())
			.build();

		when(authService.signUp(any(SignUpDto.Request.class))).thenReturn(response);


		// when, then
		mockMvc.perform(post("/api/v1/auth/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(signUpSuccess)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email").value("yglee@gmail.com"))
			.andDo(print());
	}

	@DisplayName("회원가입 : 파라미터가 잘못된 잘못된 경우 1002 코드를 반환한다.")
	@Test
	void sign_up_test_2() throws Exception {
		// given
		String wrongEmail = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/signup/wrong_email.json");
		String wrongPassword = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/signup/wrong_email.json");

		// when, then
		// 이메일 검증
		mockMvc.perform(post("/api/v1/auth/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongEmail)
			)
			.andExpect(status().is4xxClientError())
			.andExpect(header().string(RESULT_CODE, ResponseCode.ILLEGAL_ARGUMENT.getCode()))
			.andDo(print());

		// 패스워드 검증
		mockMvc.perform(post("/api/v1/auth/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongPassword)
			)
			.andExpect(status().is4xxClientError())
			.andExpect(header().string(RESULT_CODE, ResponseCode.ILLEGAL_ARGUMENT.getCode()))
			.andDo(print());
	}

	@DisplayName("로그인 : 정상 로그인")
	@Test
	void log_in_test_1() throws Exception {
		// given
		String successLogin = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/login/success_login.json");
		LoginDto.Request loginRequest = JsonUtil.converObjFromJsonStr(successLogin, LoginDto.Request.class);

		LoginDto.Response response = LoginDto.Response.builder()
			.tokenInfo(TokenInfo.builder()
				.accessToken("SUCCESS-ACCESS-TOKEN")
				.refreshToken("SUCCESS-REFRESH-TOKEN")
				.build())
			.userInfo(LoginDto.UserInfo.builder()
				.email(loginRequest.getEmail())
				.role(String.valueOf(MemberRole.OWNER))
				.build())
			.build();

		when(authService.login(any(LoginDto.Request.class))).thenReturn(response);

		// when, then
		mockMvc.perform(post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(successLogin)
			)
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().string(JsonUtil.converStrFromObj(response)))
			.andDo(print());
	}

}
