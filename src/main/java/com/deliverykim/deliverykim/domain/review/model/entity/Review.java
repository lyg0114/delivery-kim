package com.deliverykim.deliverykim.domain.review.model.entity;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.deliverykim.deliverykim.domain.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
