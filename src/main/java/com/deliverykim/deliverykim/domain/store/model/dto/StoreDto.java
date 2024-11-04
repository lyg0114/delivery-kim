package com.deliverykim.deliverykim.domain.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.store.model.dto
 * @since : 2024. 11. 3.
 */
@Builder
@Getter
public class StoreDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
    }

    @Builder
    @Getter
    public static class Response {
        private String email;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {
    }

}
