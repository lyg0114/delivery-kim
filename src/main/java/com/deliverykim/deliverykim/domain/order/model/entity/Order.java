package com.deliverykim.deliverykim.domain.order.model.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.Comment;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.order.model.define.OrderStatus;

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
 * @package : com.deliverykim.deliverykim.domain.order.model.entity
 * @since : 01.11.24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Comment("일반유저")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member user;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Comment("주문 상태")
	@Column(name = "order_status")
	private OrderStatus orderStatus = OrderStatus.PREPARE;

}
