package com.deliverykim.deliverykim.domain.menu.model.entity;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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

	@Builder.Default
	@Comment("메뉴 삭제 여부")
	@Column(name = "is_Delete")
	private boolean isDeleted = false;

}
