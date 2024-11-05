package com.deliverykim.deliverykim.domain.menu.model.dto;

import com.deliverykim.deliverykim.domain.menu.model.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : leeyounggyo
 * @package : com.deliverykim.deliverykim.domain.menu.model.dto
 * @since : 2024. 11. 5.
 */
public class MenuDto {

    public static Response from(Menu savedMenu) {
        return null;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long storeId;

        public Menu toEntity() {
            return null;
        }
    }

    @Builder
    @Getter
    public static class Response {
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {
    }

}
