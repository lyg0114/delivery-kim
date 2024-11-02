package com.deliverykim.deliverykim.global.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 2024. 11. 2.
 */
@Builder
@Getter
public class TokenRefreshInfo {

    private String refreshToken;

}
