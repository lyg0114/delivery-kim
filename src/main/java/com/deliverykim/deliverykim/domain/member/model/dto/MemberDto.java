package com.deliverykim.deliverykim.domain.member.model.dto;

import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.member.model.dto
 * @since : 02.11.24
 */
@Builder
@Getter
public class MemberDto {

	@Builder
	@Getter
	public static class Request {

	}

	@Builder
	@Getter
	public static class Response {
		private String email;
	}

}
