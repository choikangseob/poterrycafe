package ks.poterrycafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ks.poterrycafe.dto.request.CartSummaryRequest;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartSummaryId;
    private int cartId;
    private int totalItems;
    private int totalPrice;


    public CartSummary(int cartId, int totalItems, int totalPrice) {
        this.cartSummaryId = cartSummaryId;
        this.cartId = cartId;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    public static CartSummary from(int cartId,int totalItems,int totalPrice) {
        return new CartSummary(cartId,totalItems,totalPrice);
    }
}
