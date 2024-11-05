package com.deliverykim.deliverykim.domain.store.repository;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : iyeong-gyo
 * @package : com.deliverykim.deliverykim.domain.member.repository
 * @since : 02.11.24
 */
public interface StoreRepository extends JpaRepository<Store, Long> {

    long countByOwner(Member owner);

    Page<Store> findAllByStoreName(String storeName, Pageable pageable);

}
