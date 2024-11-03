package com.deliverykim.deliverykim.unit.global.auth.controller;

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

import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
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
	void signUp() throws Exception {
		// given
		String requestJsonStr = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/signUp/success.json");
		SignUpDto.Request request = JsonUtil.converObjectFromJsonStr(requestJsonStr, SignUpDto.Request.class);

		SignUpDto.Response response = SignUpDto.Response.builder()
			.email(request.getEmail())
			.build();

		when(authService.signUp(any(SignUpDto.Request.class))).thenReturn(response);


		// when, then
		mockMvc.perform(post("/api/v1/auth/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJsonStr)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email").value("yglee@gmail.com"))
			.andDo(print());
	}

}
