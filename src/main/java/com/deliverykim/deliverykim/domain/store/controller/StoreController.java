package com.deliverykim.deliverykim.domain.store.controller;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.store.service.StoreService;
import com.deliverykim.deliverykim.global.aop.model.Authorized;
import com.deliverykim.deliverykim.global.exception.CommonResponseEntity;
import com.deliverykim.deliverykim.global.exception.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.store.controller
 * @since : 2024. 11. 3.
 */
@Slf4j
@Authorized(MemberRole.OWNER)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/search")
    public CommonResponseEntity<Object> findStores(@ModelAttribute Object obj) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
    }

    @GetMapping("/search/{store-id}")
    public CommonResponseEntity<Object> getStore(@PathVariable(name = "store-id") Long storeId) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
    }

    @PostMapping("/")
    public CommonResponseEntity<Object> createStore(@Valid @RequestBody Object obj) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
    }

    @PatchMapping("/{store-id}")
    public CommonResponseEntity<Object> updateStore(@Valid @RequestBody Object obj) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
    }

    @PatchMapping("/{store-id}/close")
    public CommonResponseEntity<Object> closeStore(@PathVariable(name = "store-id") Long storeId) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, null);
    }

}

