package com.deliverykim.deliverykim.domain.menu.model.dto;

import com.deliverykim.deliverykim.domain.menu.model.entity.Menu;
import com.deliverykim.deliverykim.domain.store.model.entity.Store;
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
        return Response.builder()
                .storeName(savedMenu.getStore().getStoreName())
                .menuName(savedMenu.getMenuName())
                .build();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long storeId;
        private String menuName;

        public Menu toEntity(Store store) {
            return Menu.builder()
                    .store(store)
                    .menuName(menuName)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Response {
        private String storeName;
        private String menuName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {
    }

}
