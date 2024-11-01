package com.deliverykim.deliverykim.domain.menu.model.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.Comment;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;

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
 * @package : com.deliverykim.deliverykim.domain.menu.model.entity
 * @since : 01.11.24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "menu_id")
	private Long id;

	@Comment("가게정보")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	@Comment("메뉴 명")
	@Column(name = "menu_name")
	private String menuName;

	@Comment("메뉴 삭제 여부")
	@Column(name = "is_Delete")
	@Builder.Default
	private boolean isDelete = false;

}
