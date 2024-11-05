package com.deliverykim.deliverykim.domain.menu.repository;

import com.deliverykim.deliverykim.domain.menu.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.menu.repository
 * @since : 2024. 11. 5.
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
