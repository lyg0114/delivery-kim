package com.deliverykim.deliverykim.domain.store.service;

import com.deliverykim.deliverykim.domain.store.model.dto.StoreDto;
import com.deliverykim.deliverykim.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.store.service
 * @since : 2024. 11. 3.
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreDto.Response findStores(StoreDto.Search storeSearch) {
        return null;
    }

    public StoreDto.Response getStore(Long storeId) {
        return null;
    }

    public StoreDto.Response createStore(StoreDto.Request storeRequest) {
        return null;
    }

    public void updateStore(StoreDto.Request storeRequest) {

    }

    public void closeStore(Long storeId) {
    }
}
