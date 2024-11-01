package com.deliverykim.deliverykim.domain.order.model.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.store.model.define
 * @since : 01.11.24
 */
@Getter
@RequiredArgsConstructor
public enum OrderStatus {

	PREPARE("준비중"),
	COOKING("조리중"),
	DELIVERY("배달중"),
	COMPLETE("완료")
	;

	private final String name;

}
