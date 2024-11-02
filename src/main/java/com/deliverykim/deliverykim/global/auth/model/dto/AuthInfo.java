package com.deliverykim.deliverykim.global.auth.model.dto;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
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
    private String password;
    private MemberRole memberRole;

}
