package com.deliverykim.deliverykim.global.config;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.global.config
 * @since : 01.11.24
 */
@Slf4j
@Component
public class PasswordEncoder {

	public String encode(String rawPassword) {
		return BCrypt.withDefaults()
			.hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return BCrypt.verifyer()
			.verify(rawPassword.toCharArray(), encodedPassword)
			.verified;
	}

}

