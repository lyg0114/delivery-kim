package com.deliverykim.deliverykim.domain.member.model.entity;

import com.deliverykim.deliverykim.domain.common.BaseEntity;
import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.menu.model.dto.MenuDto;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.OWNER_STORE_MISMATCH;
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
@Table(name = "members")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@OneToMany(mappedBy = "owner" ,fetch = FetchType.LAZY)
	private List<Store> myStores;

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
	@Column(name = "is_withdrawal")
	private boolean isWithdrawal = false;

	public void doWithdrawal() {
		isWithdrawal = true;
	}

	public void hasStore(MenuDto.Request menuRequest) {
		myStores.forEach(myStore-> {
			if (!myStore.getId().equals(menuRequest.getStoreId())) {
				throw new UserHandlerException(OWNER_STORE_MISMATCH);
			}
		});
	}

}
