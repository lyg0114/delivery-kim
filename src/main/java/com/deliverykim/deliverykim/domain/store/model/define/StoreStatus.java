package com.deliverykim.deliverykim.domain.store.model.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.store.model.define
 * @since : 01.11.24
 */
@Getter
@RequiredArgsConstructor
public enum StoreStatus {

	OPERATING("운영중"),
	CLOSED("폐업");

	private final String name;

}
