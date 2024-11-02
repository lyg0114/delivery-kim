package com.deliverykim.deliverykim.global.auth.model.dto;

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
public class WithdrawalDto {

	@Builder
	@Getter
	public static class Request {
		@NotBlank
		private String email;

		@NotBlank
		private String password;
	}

	@Builder
	@Getter
	public static class Response {
	}

}
