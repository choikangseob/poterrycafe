package ks.poterrycafe.dto.request;

public record UpdateCartItemsRequest(
        long cartItemsId,
        long cartId,
        long productId,
        long quantity,
        long price
) {
}
