package ks.poterrycafe.dto.request;

public record SaveProductRequest(
        int product_id,
        String product_name,
        String description,
        int quantity,
        int price
) {
}
