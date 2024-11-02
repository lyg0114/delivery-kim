package com.deliverykim.deliverykim.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.member.repository
 * @since : 02.11.24
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByEmail(String email);

	Optional<Member> findByEmail(String username);
}
