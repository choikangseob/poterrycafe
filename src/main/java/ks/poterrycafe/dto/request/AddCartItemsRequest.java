package ks.poterrycafe.dto.request;

public record AddCartItemsRequest(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {
}
