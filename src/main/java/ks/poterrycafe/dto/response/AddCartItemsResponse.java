package ks.poterrycafe.dto.response;

import ks.poterrycafe.entity.CartItems;
import lombok.Builder;

@Builder
public record AddCartItemsResponse(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {
    public static AddCartItemsResponse of(CartItems addCartItems) {

        return AddCartItemsResponse.builder()
                .cartItemsId(addCartItems.getCartItemsId())
                .cartId(addCartItems.getCartId())
                .productId(addCartItems.getProductId())
                .quantity(addCartItems.getQuantity())
                .price(addCartItems.getPrice())
                .build();
    }
}
