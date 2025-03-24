package ks.poterrycafe.dto.response;


import ks.poterrycafe.entity.Product;
import lombok.Builder;

@Builder
public record SaveProductResponse(
        int Product_id,
        String Product_name,
        String description,
        int quantity,
        int price
) {
    public static SaveProductResponse of(Product savedProduct) {
        return SaveProductResponse.builder()
                .Product_id(savedProduct.getProduct_id())
                .Product_name(savedProduct.getProduct_name())
                .description(savedProduct.getDescription())
                .quantity(savedProduct.getQuantity())
                .price(savedProduct.getPrice()).
                build();
    }
}
