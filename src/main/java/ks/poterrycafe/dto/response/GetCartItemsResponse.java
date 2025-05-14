package ks.poterrycafe.dto.response;

import ks.poterrycafe.entity.CartItems;
import lombok.Builder;

@Builder
public record GetCartItemsResponse(
        String productName,
        String description,
        int quantity,
        int price
) {

    public static GetCartItemsResponse of(String productName, String description, int quantity, int price) {
        return GetCartItemsResponse.builder()
                .productName(productName)
                .description(description)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
