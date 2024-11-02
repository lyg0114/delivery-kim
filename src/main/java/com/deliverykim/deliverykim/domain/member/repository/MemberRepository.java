package com.deliverykim.deliverykim.domain.member.repository;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.member.repository
 * @since : 02.11.24
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByEmailAndIsDeletedFalse(String email);

	Optional<Member> findByEmail(String username);

}
