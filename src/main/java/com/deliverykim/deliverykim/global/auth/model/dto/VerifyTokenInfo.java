package com.deliverykim.deliverykim.global.auth.model.dto;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 2024. 11. 2.
 */
@Builder
@Getter
public class VerifyTokenInfo {

    private String email;
    private MemberRole memberRole;
    private Date expirateDate;

}
