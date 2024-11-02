package com.deliverykim.deliverykim.global.auth.service;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.SignUpDto;
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

	public SignUpDto.Response register(SignUpDto.Request signupRequest) {
		validateDuplicatedEmail(signupRequest);

		Member savedMember = memberRepository.save(signupRequest.toEntity());
		return SignUpDto.from(savedMember);
	}

	private void validateDuplicatedEmail(SignUpDto.Request signupRequest) {
		if (memberRepository.existsByEmail(signupRequest.getEmail())) {
			throw new UserHandlerException(ALREADY_EXIST_EMAIL);
		}
	}
}
