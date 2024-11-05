package com.deliverykim.deliverykim.domain.store.model.dto;

import com.deliverykim.deliverykim.domain.member.model.entity.Member;
import com.deliverykim.deliverykim.domain.store.model.define.StoreStatus;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.store.model.dto
 * @since : 2024. 11. 3.
 */
@Builder
@Getter
public class StoreDto {

    public static Response from(Store savedStore) {
        return StoreDto.Response.builder()
                .build();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private LocalDateTime openTime;

        @NotBlank
        private LocalDateTime closeTime;

        @NotBlank
        private String storeName;

        @NotBlank
        private BigDecimal minimumOrderPrice;

        public Store toEntity(Member owner) {
            return Store.builder()
                    .owner(owner)
                    .storeName(getStoreName())
                    .openTime(getOpenTime())
                    .closeTime(getCloseTime())
                    .minimumOrderPrice(getMinimumOrderPrice())
                    .storeStatus(StoreStatus.OPERATING)
                    .build();
        }

    }

    @Builder
    @Getter
    public static class Response {
        private String ownerEmail;
        private String storeName;
        private LocalDateTime openTime;
        private LocalDateTime closeTime;
        private BigDecimal minimumOrderPrice;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {
    }

}
