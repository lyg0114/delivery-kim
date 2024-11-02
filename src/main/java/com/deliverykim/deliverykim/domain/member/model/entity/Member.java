package com.deliverykim.deliverykim.domain.member.model.entity;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.Comment;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Comment("사용자 이메일")
	@Column(name = "email")
	private String email;

	@Comment("사용자 패스워드")
	@Column(name = "password")
	private String password;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Comment("회원의 권한")
	@Column(name = "member_role")
	private MemberRole memberRole = MemberRole.USER;

	@Builder.Default
	@Comment("회원의 탈퇴 여부")
	@Column(name = "is_delete")
	private boolean isDelete = false;

}
