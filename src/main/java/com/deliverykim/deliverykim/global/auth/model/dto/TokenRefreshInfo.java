package com.deliverykim.deliverykim.global.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 2024. 11. 2.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshInfo {

    @NotBlank
    private String refreshToken;

}
