package ks.poterrycafe.dto.response;


import ks.poterrycafe.entity.Product;
import lombok.Builder;

@Builder
public record SaveProductResponse(
        int productId,
        String productName,
        String description,
        int quantity,
        int price
) {
    public static SaveProductResponse of(Product savedProduct) {
        return SaveProductResponse.builder()
                .productId(savedProduct.getProductId())
                .productName(savedProduct.getProductName())
                .description(savedProduct.getDescription())
                .quantity(savedProduct.getQuantity())
                .price(savedProduct.getPrice()).
                build();
    }
}
