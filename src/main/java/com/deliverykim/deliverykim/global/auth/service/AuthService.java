package com.deliverykim.deliverykim.global.auth.service;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.AuthInfo;
import com.deliverykim.deliverykim.global.auth.model.dto.LoginDto;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.auth.model.dto.TokenInfo;
import com.deliverykim.deliverykim.global.config.PasswordEncoder;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.*;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.auth.service
 * @since : 02.11.24
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenManager tokenManager;

	public SignUpDto.Response signUp(SignUpDto.Request signupRequest) {
		validateDuplicatedEmail(signupRequest.getEmail());

		Member savedMember = memberRepository.save(signupRequest.toEntity(passwordEncoder));
		return SignUpDto.from(savedMember);
	}

	public LoginDto.Response login(LoginDto.Request loginRequest) {
		Member findMember = memberRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

		validatePassword(findMember);

		TokenInfo tokenInfo = tokenManager.generateAuthenticationToken(AuthInfo.from(findMember));

		return LoginDto.Response.builder()
				.tokenInfo(tokenInfo)
				.userInfo(LoginDto.from(findMember))
				.build();
	}

	private void validateDuplicatedEmail(String email) {
		if (memberRepository.existsByEmailAndIsDeletedFalse(email)) {
			throw new UserHandlerException(ALREADY_EXIST_EMAIL);
		}
	}

	private void validatePassword(Member member) {
		if (!passwordEncoder.matches(member.getPassword(), member.getPassword())) {
			throw new UserHandlerException(DO_NOT_MATCH_PASSWORLD);
		}
	}

}
