package com.deliverykim.deliverykim.domain.member.model.entity;

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
 * @package : com.deliverykim.deliverykim.domain.menu.model.entity
 * @since : 01.11.24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

}
