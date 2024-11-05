package com.deliverykim.deliverykim.domain.menu.service;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.domain.menu.model.dto.MenuDto;
import com.deliverykim.deliverykim.domain.menu.model.entity.Menu;
import com.deliverykim.deliverykim.domain.menu.repository.MenuRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.VerifyTokenInfo;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.DO_NOT_EXIST_EMAIL;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.menu.service
 * @since : 2024. 11. 5.
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    public Page<MenuDto.Response> findMenus(MenuDto.Search menuSearch, Pageable pageable) {
        return null;
    }

    public MenuDto.Response getMenu(Long menuId) {
        return null;
    }

    public MenuDto.Response createMenu(MenuDto.Request menuRequest) {
        VerifyTokenInfo currentRequestMeberInfo = tokenManager.getCurrentRequestMeberInfo();
        Member member = memberRepository.findByEmail(currentRequestMeberInfo.getEmail())
                .orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

        // 본인 가게인지 검증
        member.hasStore(menuRequest);

        Menu savedMenu = menuRepository.save(menuRequest.toEntity());

        return MenuDto.from(savedMenu);
    }

    public void updateMenu(Long menuId, MenuDto.Request menuRequest) {

    }

    public void deleteStore(Long menuId) {

    }

}
