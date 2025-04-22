package ks.poterrycafe.dto.response;

import ks.poterrycafe.entity.CartItems;
import lombok.Builder;

@Builder
public record DeleteCartItemsResponse(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {

    public static DeleteCartItemsResponse of(CartItems cartItemsTobeDelete) {

        return DeleteCartItemsResponse.builder()
                .cartItemsId(cartItemsTobeDelete.getCartItemsId())
                .cartId(cartItemsTobeDelete.getCartId())
                .productId(cartItemsTobeDelete.getProductId())
                .quantity(cartItemsTobeDelete.getQuantity())
                .price(cartItemsTobeDelete.getPrice())
                .build();
    }
}
