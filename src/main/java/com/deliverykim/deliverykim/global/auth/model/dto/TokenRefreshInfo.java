package com.deliverykim.deliverykim.global.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String refreshToken;

}
