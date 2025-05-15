package ks.poterrycafe.dto.request;

public record CartSummaryRequest(
        int cartSummaryId,
        int cartId,
        int totalItems,
        int totalPrice
) {
}
