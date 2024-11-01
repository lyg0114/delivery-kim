package com.deliverykim.deliverykim.domain.member.model.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.member.model.define
 * @since : 01.11.24
 */
@Getter
@RequiredArgsConstructor
public enum MemberRole {

	OWNER("사장님"),
	USER("일반유저");

	private final String name;

}
