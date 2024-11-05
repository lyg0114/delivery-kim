package com.deliverykim.deliverykim.domain.store.controller;

import com.deliverykim.deliverykim.domain.member.model.define.MemberRole;
import com.deliverykim.deliverykim.domain.store.model.dto.StoreDto;
import com.deliverykim.deliverykim.domain.store.service.StoreService;
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
 * @package : com.deliverykim.deliverykim.domain.store.controller
 * @since : 2024. 11. 3.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/search")
    public CommonResponseEntity<Page<StoreDto.Response>> findStores(@ModelAttribute StoreDto.Search storeSearch,
                                                                    Pageable pageable) {

        return new CommonResponseEntity<>(ResponseCode.SUCCESS, storeService.findStores(storeSearch, pageable));
    }

    @GetMapping("/search/{store-id}")
    public CommonResponseEntity<StoreDto.Response> getStore(@PathVariable(name = "store-id") Long storeId) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, storeService.getStore(storeId));
    }

    @Authorized(MemberRole.OWNER)
    @PostMapping("/")
    public CommonResponseEntity<StoreDto.Response> createStore(@Valid @RequestBody StoreDto.Request storeRequest) {
        return new CommonResponseEntity<>(ResponseCode.SUCCESS, storeService.createStore(storeRequest));
    }

    @Authorized(MemberRole.OWNER)
    @PatchMapping("/{store-id}")
    public CommonResponseEntity<Void> updateStore(@PathVariable(name = "store-id") Long storeId,
                                                  @Valid @RequestBody StoreDto.Request storeRequest) {

        storeService.updateStore(storeId, storeRequest);
        return new CommonResponseEntity<>(ResponseCode.SUCCESS);
    }

    @Authorized(MemberRole.OWNER)
    @PatchMapping("/{store-id}/close")
    public CommonResponseEntity<Void> closeStore(@PathVariable(name = "store-id") Long storeId) {
        storeService.closeStore(storeId);
        return new CommonResponseEntity<>(ResponseCode.SUCCESS);
    }

}

