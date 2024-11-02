package com.deliverykim.deliverykim.global.auth.model.dto;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 2024. 11. 2.
 */
@Builder
@Getter
public class AuthInfo {

    private String email;
    private MemberRole memberRole;

    public static AuthInfo from(Member member) {
        return AuthInfo.builder()
                .email(member.getEmail())
                .memberRole(member.getMemberRole())
                .build();
    }
}
