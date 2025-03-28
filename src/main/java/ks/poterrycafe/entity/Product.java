package ks.poterrycafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ks.poterrycafe.common.entity.EssentialColumns;
import ks.poterrycafe.dto.request.SaveProductRequest;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends EssentialColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int product_id;
    private String product_name;
    private String description;
    private int quantity;
    private int price;

    public Product(SaveProductRequest saveProductRequest) {
        this.product_id = saveProductRequest.product_id();
        this.product_name = saveProductRequest.product_name();
        this.description = saveProductRequest.description();
        this.quantity = saveProductRequest.quantity();
        this.price = saveProductRequest.price();
    }

    public static Product from(SaveProductRequest saveProductRequest) {

        return new Product(saveProductRequest);
    }
}
