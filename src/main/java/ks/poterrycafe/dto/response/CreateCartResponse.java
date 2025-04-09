package ks.poterrycafe.dto.response;

import ks.poterrycafe.dto.request.CreateCartRequest;
import ks.poterrycafe.entity.Cart;
import lombok.Builder;

@Builder
public record CreateCartResponse(
        long cartId
) {

    public static CreateCartResponse of(Cart createdCart) {
        return CreateCartResponse.builder()
                .cartId(createdCart.getCartId())
                        .build();
    }
}
