package ks.poterrycafe.dto.request;

public record UpdateProductRequest(
        int productId,
        String productName,
        String description,
        int quantity,
        int price
) {
}
