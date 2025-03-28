package ks.poterrycafe.dto.request;

public record SaveProductRequest(
        int productId,
        String productName,
        String description,
        int quantity,
        int price
) {
}
