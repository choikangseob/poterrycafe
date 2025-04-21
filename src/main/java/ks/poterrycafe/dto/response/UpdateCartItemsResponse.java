package ks.poterrycafe.dto.response;

import ks.poterrycafe.entity.CartItems;
import lombok.Builder;

@Builder
public record UpdateCartItemsResponse(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {

    public static UpdateCartItemsResponse of(CartItems updateCartItems){
        return UpdateCartItemsResponse.builder()
                .cartItemsId(updateCartItems.getCartItemsId())
                .cartId(updateCartItems.getCartId())
                .productId(updateCartItems.getProductId())
                .quantity(updateCartItems.getQuantity())
                .price(updateCartItems.getPrice())
                .build();
    }
}
