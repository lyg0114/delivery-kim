package com.deliverykim.deliverykim.unit.global.auth.controller;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.global.auth.model.dto.*;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import com.deliverykim.deliverykim.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.deliverykim.deliverykim.global.auth.filter.AuthorizationFilter.REFRESH_TOKEN;
import static com.deliverykim.deliverykim.global.exception.ResponseCode.RESULT_CODE;
import static com.deliverykim.deliverykim.global.exception.ResponseCode.WITHDRAWAL_EMAIL;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.controller
 * @since : 2024. 11. 2.
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @MockBean
    AuthService authService;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TokenManager tokenManager;

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

    @DisplayName("회원탈퇴 : 정상 탈퇴")
    @Test
    void withdrawal_test_1() throws Exception {
        // given
        AuthInfo authInfo = AuthInfo.builder()
                .email("yglee@gmail.com")
                .memberRole(MemberRole.USER)
                .build();

        TokenInfo tokenInfo = tokenManager.generateAuthenticationToken(authInfo);
        String successWithdrawal = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/withdrawal/success_withdrawal.json");

        doNothing().when(authService).withdrawal(any(WithdrawalDto.Request.class));

        // when, then
        mockMvc.perform(delete("/api/v1/auth/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, tokenInfo.getAccessToken())
                        .header(REFRESH_TOKEN, tokenInfo.getRefreshToken())
                        .content(successWithdrawal)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string(RESULT_CODE, ResponseCode.SUCCESS.getCode()))
                .andDo(print());
    }

    @DisplayName("회원탈퇴 : 이미 탈퇴한 계정")
    @Test
    void withdrawal_test_2() throws Exception {
        // given
        AuthInfo authInfo = AuthInfo.builder()
                .email("yglee@gmail.com")
                .memberRole(MemberRole.USER)
                .build();

        TokenInfo tokenInfo = tokenManager.generateAuthenticationToken(authInfo);
        String failWithdrawal = JsonUtil.readJsonStrFromFile("deliverykim/unit/auth/controller/withdrawal/fail_withdrawal.json");

        doThrow(new UserHandlerException(WITHDRAWAL_EMAIL))
                .when(authService)
                .withdrawal(any(WithdrawalDto.Request.class));

        // when, then
        mockMvc.perform(delete("/api/v1/auth/withdrawal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION, tokenInfo.getAccessToken())
                        .header(REFRESH_TOKEN, tokenInfo.getRefreshToken())
                        .content(failWithdrawal)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(header().string(RESULT_CODE, ResponseCode.WITHDRAWAL_EMAIL.getCode()))
                .andDo(print());
    }


}
