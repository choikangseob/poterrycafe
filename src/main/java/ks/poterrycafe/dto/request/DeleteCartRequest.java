package ks.poterrycafe.dto.request;

public record DeleteCartRequest(
        long cartId,
        long createMemberId
) {
}
