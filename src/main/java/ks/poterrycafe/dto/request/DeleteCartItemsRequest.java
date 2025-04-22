package ks.poterrycafe.dto.request;

public record DeleteCartItemsRequest(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {
}
