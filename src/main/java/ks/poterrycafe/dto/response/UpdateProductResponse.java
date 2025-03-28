package ks.poterrycafe.dto.response;

import ks.poterrycafe.dto.request.UpdateProductRequest;
import ks.poterrycafe.entity.Product;
import lombok.Builder;

@Builder
public record UpdateProductResponse(
        int productId,
        String productName,
        String description,
        int quantity,
        int price
) {
    public static UpdateProductResponse of(Product updateProduct) {
        return UpdateProductResponse.builder()
                .productName(updateProduct.getProductName())
                .description(updateProduct.getDescription())
                .quantity(updateProduct.getQuantity())
                .price(updateProduct.getPrice())
                .build();
    }
}
