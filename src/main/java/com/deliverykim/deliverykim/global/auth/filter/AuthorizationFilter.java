package com.deliverykim.deliverykim.global.auth.filter;

import com.deliverykim.deliverykim.global.auth.service.AuthService;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import com.deliverykim.deliverykim.global.exception.custom.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.net.URLEncoder;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.RESULT_CODE;
import static com.deliverykim.deliverykim.global.exception.ResponseCode.RESULT_MESSAGE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    public static final String REFRESH_TOKEN = "Refresh-token";

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (validateExcludeUrl(request, response, filterChain)) return;

        try {
            tokenManager.verifyAccessToken(request.getHeader(AUTHORIZATION));
            tokenManager.verifyRefreshToken(request.getHeader(REFRESH_TOKEN));
        } catch (AuthenticationException ex) {
            jwtExceptionHandler(response, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    private boolean validateExcludeUrl(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        return false;
    }

    @SneakyThrows
    public void jwtExceptionHandler(HttpServletResponse response, AuthenticationException responseCode) {
        ResponseCode authErrorResponseCode = ResponseCode.getResponseCode(responseCode.getResponseCode().getCode());
        response.setStatus(authErrorResponseCode.getHttpStatusCode().value());
        response.setHeader(RESULT_CODE, authErrorResponseCode.getCode());
        response.setHeader(RESULT_MESSAGE, URLEncoder.encode(authErrorResponseCode.getMessage(), UTF_8));
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());
    }

}
