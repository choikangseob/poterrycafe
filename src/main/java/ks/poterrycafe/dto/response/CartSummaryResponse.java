package ks.poterrycafe.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CartSummaryResponse(
        int cartId,
        int totalItems,
        int totalPrice
) {

}
