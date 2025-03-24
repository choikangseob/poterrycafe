package ks.poterrycafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ks.poterrycafe.dto.request.SaveProductRequest;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int product_id;
    private String product_name;
    private String description;
    private int quantity;
    private int price;

    public Product(SaveProductRequest saveProductRequest) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public static Product from(SaveProductRequest saveProductRequest) {

        return new Product(saveProductRequest);
    }
}
