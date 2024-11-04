package com.deliverykim.deliverykim.global.aop;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.global.aop.model.Authorized;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 접근한 자원에 대해 권한이 있는지 체크
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AccessControlConfig {

    private final TokenManager tokenManager;

    @Before("@within(authorized) || @annotation(authorized)")
    private void authorization(JoinPoint joinPoint, Authorized authorized) {

        // 메서드 수준의 어노테이션이 없을 경우 클래스 수준 어노테이션 검사
        if (authorized == null) {
            Class<?> targetClass = joinPoint.getTarget().getClass();
            authorized = targetClass.getAnnotation(Authorized.class);
        }

        // 어노테이션이 없는 경우 실행 종료, memeberRole에 상관없이 접근가능한 API
        if (authorized == null) {
            return;
        }

        MemberRole currentMemberRole = tokenManager.getCurrentRequestMeberInfo().getMemberRole();
        MemberRole accessRole = authorized.value();

        // 인증 로직 수행
        if (currentMemberRole != accessRole) {
            log.info("권한이 없는 요청입니다.");
            throw new UserHandlerException(ResponseCode.TOKEN_NOT_AUTHORIZED);
        }
    }

}