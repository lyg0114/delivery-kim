package com.deliverykim.deliverykim.global.auth.controller;

import com.deliverykim.deliverykim.global.auth.filter.AuthorizationFilter;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.controller
 * @since : 2024. 11. 2.
 */
@WebMvcTest(AuthController.class)
class AuthControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;
    @MockBean
    private TokenManager tokenManager;

    MockMvc build;

    @BeforeEach
    void setUp() {
        build = MockMvcBuilders.standaloneSetup(new AuthController(authService))
                .addFilter(new AuthorizationFilter(tokenManager))  // 필터 추가
                .build();
    }

    @DisplayName("회원가입 : 정상 회원가입")
    @Test
    void signUp() throws Exception {
        SignUpDto.Response response = SignUpDto.Response.builder()
                .email("yglee@gmail.com")
                .build();

        when(authService.signUp(any(SignUpDto.Request.class))) .thenReturn(response);

        build.perform(post("/api/v1/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{" +
//                                "  \"email\": \"yglee@gmail.com\",\n" +
//                                "  \"password\": \"Dudrydudry!1\",\n" +
//                                "  \"comparePassword\": \"Dudrydudry!1\",\n" +
//                                "  \"memberRole\": \"OWNER\"\n" +
//                                "}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("yglee@gmail.com"))
                .andDo(print());
    }

}