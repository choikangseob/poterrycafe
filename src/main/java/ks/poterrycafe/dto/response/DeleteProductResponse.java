package ks.poterrycafe.dto.response;

import ks.poterrycafe.dto.request.DeleteProductRequest;
import ks.poterrycafe.entity.Product;
import lombok.Builder;

@Builder
public record DeleteProductResponse(
        long productId,
        String productName,
        String description,
        int quantity,
        int price,
        long createMemberId
) {

    public static DeleteProductResponse of(Product productTobeDelete) {
        return DeleteProductResponse.builder()
                .productId(productTobeDelete.getProductId())
                .productName(productTobeDelete.getProductName())
                .description(productTobeDelete.getDescription())
                .quantity(productTobeDelete.getQuantity())
                .price(productTobeDelete.getPrice())
                .createMemberId(productTobeDelete.getCreateMemberId())
                .build();
    }
}
