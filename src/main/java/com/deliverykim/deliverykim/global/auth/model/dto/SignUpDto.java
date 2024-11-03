package com.deliverykim.deliverykim.global.auth.model.dto;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.global.config.PasswordEncoder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.auth.model.dto
 * @since : 02.11.24
 */
@Builder
@Getter
public class SignUpDto {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {

		@NotBlank
		@Email
		private String email;

		@NotBlank
		@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/\\-]).{8,}$",
			message = "비밀번호는 최소 8글자 이상이며, 대문자, 소문자, 숫자, 특수문자를 각각 1글자 이상 포함해야 합니다."
		)
		private String password;

		@NotBlank
		@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/\\-]).{8,}$",
			message = "비밀번호는 최소 8글자 이상이며, 대문자, 소문자, 숫자, 특수문자를 각각 1글자 이상 포함해야 합니다."
		)
		private String comparePassword;

		@NotBlank
		private String memberRole;

		public Member toEntity(PasswordEncoder passwordEncoder) {
			return Member.builder()
					.email(email)
					.password(passwordEncoder.encode(password))
					.memberRole(MemberRole.valueOf(memberRole))
					.build();
		}
	}

	@Builder
	@Getter
	public static class Response {
		private String email;
	}

	public static Response from(Member member) {
		return Response.builder()
			.email(member.getEmail())
			.build();
	}

}
