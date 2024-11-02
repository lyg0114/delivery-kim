package com.deliverykim.deliverykim.global.auth.model.dto;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 02.11.24
 */
@Builder
@Getter
public class LoginDto {

    @Builder
    @Getter
    public static class Request {

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;

    }

    @Builder
    @Getter
    public static class Response {
        private UserInfo userInfo;
        private TokenInfo tokenInfo;
    }

    @Builder
    @Getter
    public static class UserInfo {
        private String email;
        private String role;
    }

    public static UserInfo from(Member member) {
        return UserInfo.builder()
                .email(member.getEmail())
                .role(String.valueOf(member.getMemberRole()))
                .build();
    }

}
