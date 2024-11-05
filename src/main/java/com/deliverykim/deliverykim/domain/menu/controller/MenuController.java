package com.deliverykim.deliverykim.domain.menu.controller;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.menu.model.dto.MenuDto;
import com.deliverykim.deliverykim.domain.menu.service.MenuService;
import com.deliverykim.deliverykim.global.aop.model.Authorized;
import com.deliverykim.deliverykim.global.exception.CommonResponseEntity;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.menu.controller
 * @since : 2024. 11. 5.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/search")
    public CommonResponseEntity<Page<MenuDto.Response>> findMenus(@ModelAttribute MenuDto.Search menuSearch,
                                                                  Pageable pageable) {

        return new CommonResponseEntity<>(ResponseCode.SUCCESS, menuService.findMenus(menuSearch, pageable));
    }

    @GetMapping("/search/{menu-id}")
    public CommonResponseEntity<MenuDto.Response> getMenu(@PathVariable(name = "menu-id") Long menuId) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, menuService.getMenu(menuId));
    }

    @Authorized(MemberRole.OWNER)
    @PostMapping("/")
    public CommonResponseEntity<MenuDto.Response> createMenu(@Valid @RequestBody MenuDto.Request menuRequest) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, menuService.createMenu(menuRequest));
    }

    @PatchMapping("/{menu-id}")
    public CommonResponseEntity<Void> updateMenu(@PathVariable(name = "menu-id") Long menuId,
                                                  @Valid @RequestBody MenuDto.Request menuRequest) {

        menuService.updateMenu(menuId, menuRequest);
        return new CommonResponseEntity<>(ResponseCode.SUCCESS);
    }

    @DeleteMapping("/{menu-id}/close")
    public CommonResponseEntity<Void> closeMenu(@PathVariable(name = "menu-id") Long menuId) {
        menuService.deleteStore(menuId);
        return new CommonResponseEntity<>(ResponseCode.SUCCESS);
    }

}
