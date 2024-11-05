package com.deliverykim.deliverykim.domain.store.service;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.member.repository.MemberRepository;
import com.deliverykim.deliverykim.domain.store.model.dto.StoreDto;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
import com.deliverykim.deliverykim.domain.store.repository.StoreRepository;
import com.deliverykim.deliverykim.global.auth.model.dto.VerifyTokenInfo;
import com.deliverykim.deliverykim.global.auth.service.TokenManager;
import com.deliverykim.deliverykim.global.exception.custom.UserHandlerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.deliverykim.deliverykim.global.exception.ResponseCode.DO_NOT_EXIST_EMAIL;
import static com.deliverykim.deliverykim.global.exception.ResponseCode.STORE_COUNT_LIMIT_EXCEEDED;

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
    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    public StoreDto.Response findStores(StoreDto.Search storeSearch) {
        return null;
    }

    public StoreDto.Response getStore(Long storeId) {
        return null;
    }

    public StoreDto.Response createStore(StoreDto.Request storeRequest) {
        VerifyTokenInfo currentRequestMeberInfo = tokenManager.getCurrentRequestMeberInfo();
        Member member = memberRepository.findByEmail(currentRequestMeberInfo.getEmail())
                .orElseThrow(() -> new UserHandlerException(DO_NOT_EXIST_EMAIL));

        validateStoreCount(member);

        Store savedStore = storeRepository.save(storeRequest.toEntity(member));

        return StoreDto.from(savedStore);
    }

    // 사장이 가지고 있는 가게가 3개를 넘어 면 예외
    private void validateStoreCount(Member owner) {
        long count = storeRepository.countByOwner(owner);
        if (count > 3) {
            throw new UserHandlerException(STORE_COUNT_LIMIT_EXCEEDED);
        }
    }

    public void updateStore(StoreDto.Request storeRequest) {

    }

    public void closeStore(Long storeId) {
    }
}
