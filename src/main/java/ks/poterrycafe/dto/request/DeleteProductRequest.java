package ks.poterrycafe.dto.request;

public record DeleteProductRequest(
        long productId,
        String productName,
        String description,
        int quantity,
        int price,
        long createMemberId
) {
}
