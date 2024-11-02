package com.deliverykim.deliverykim.global.auth.service;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.*;
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
		// TODO : 탈퇴한 사용자의 계정은 재활용 할 수 없도록 처리

		Member savedMember = memberRepository.save(signupRequest.toEntity(passwordEncoder));
		return SignUpDto.from(savedMember);
	}

	public LoginDto.Response login(LoginDto.Request loginRequest) {
		Member findMember = memberRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

		validatePassword(loginRequest.getPassword(), findMember.getPassword());

		TokenInfo tokenInfo = tokenManager.generateAuthenticationToken(AuthInfo.from(findMember));

		return LoginDto.Response.builder()
				.tokenInfo(tokenInfo)
				.userInfo(LoginDto.from(findMember))
				.build();
	}

	private void validateDuplicatedEmail(String email) {
		if (memberRepository.existsByEmailAndIsWithdrawalFalse(email)) {
			throw new UserHandlerException(ALREADY_EXIST_EMAIL);
		}
	}

	private void validatePassword(String rawPassword, String encodedPassowrd) {
		if (!passwordEncoder.matches(rawPassword, encodedPassowrd)) {
			throw new UserHandlerException(DO_NOT_MATCH_PASSWORLD);
		}
	}

	public void withdrawal(WithdrawalDto.Request withdrawalRequest) {
		Member findMember = memberRepository.findByEmail(withdrawalRequest.getEmail())
				.orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

		if (findMember.isWithdrawal()) {
			throw new UserHandlerException(ALREADY_WITHDRAWAL_MEMBER);
		}

		validatePassword(withdrawalRequest.getPassword(), findMember.getPassword());

		findMember.doWithdrawal();
	}

}
