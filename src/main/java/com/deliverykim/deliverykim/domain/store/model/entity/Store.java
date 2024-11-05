package com.deliverykim.deliverykim.domain.store.model.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.deliverykim.deliverykim.domain.store.model.dto.StoreDto;
import org.hibernate.annotations.Comment;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.store.model.define.StoreStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.store.model.entity
 * @since : 01.11.24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "store")
public class Store extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "store_id")
	private Long id;

	@Comment("가게주인")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member owner;

	@Comment("가게명")
	@Column(name = "store_name")
	private String storeName;

	@Comment("오픈시간")
	@Column(name = "open_time")
	private LocalDateTime openTime;

	@Comment("마감 시간")
	@Column(name = "close_time")
	private LocalDateTime closeTime;

	@Comment("최소 주문 금액")
	@Column(name = "minimum_order_price")
	private BigDecimal minimumOrderPrice;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Comment("가게의 운영상태")
	@Column(name = "store_status")
	private StoreStatus storeStatus = StoreStatus.OPERATING;

	public void updateStore(StoreDto.Request storeRequest) {
		if (storeRequest.getStoreName() != null && !storeRequest.getStoreName().trim().isEmpty()) {
			storeName = storeRequest.getStoreName();
		}
		if (storeRequest.getOpenTime() != null) {
			openTime = storeRequest.getOpenTime();
		}
		if (storeRequest.getCloseTime() != null) {
			closeTime = storeRequest.getCloseTime();
		}
		if (storeRequest.getMinimumOrderPrice() != null) {
			minimumOrderPrice = storeRequest.getMinimumOrderPrice();
		}
	}
}
