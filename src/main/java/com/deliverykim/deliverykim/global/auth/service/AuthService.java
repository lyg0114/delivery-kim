package com.deliverykim.deliverykim.global.auth.service;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.Login;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
import com.deliverykim.deliverykim.global.config.PasswordEncoder;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

	public SignUpDto.Response signUp(SignUpDto.Request signupRequest) {
		validateDuplicatedEmail(signupRequest.getEmail());

		Member savedMember = memberRepository.save(signupRequest.toEntity(passwordEncoder));
		return SignUpDto.from(savedMember);
	}

	public Login.Response login(Login.Request loginRequest) {
		validateDuplicatedEmail(loginRequest.getEmail());
		validatePassword(loginRequest);

		return null;
	}

	private void validateDuplicatedEmail(String email) {
		if (memberRepository.existsByEmail(email)) {
			throw new UserHandlerException(ALREADY_EXIST_EMAIL);
		}
	}

	private void validatePassword(Login.Request loginRequest) {
		Member member = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

		if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
			throw new UserHandlerException(DO_NOT_MATCH_PASSWORLD);
		}
	}
}
