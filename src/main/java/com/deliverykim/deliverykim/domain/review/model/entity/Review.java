package com.deliverykim.deliverykim.domain.review.model.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.Comment;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.order.model.entity.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * @package : com.deliverykim.deliverykim.domain.review.model.entity
 * @since : 01.11.24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "review")
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@Comment("주문정보")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@Comment("별점 갯수")
	@Column(name = "stars_count")
	private int starsCount;

}
