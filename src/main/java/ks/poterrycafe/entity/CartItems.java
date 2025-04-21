package ks.poterrycafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ks.poterrycafe.dto.request.AddCartItemsRequest;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemsId;
    private long cartId;
    private long productId;
    private long quantity;
    private long price;

    public CartItems(AddCartItemsRequest addCartItemsRequest) {
        this.cartItemsId = addCartItemsRequest.cartItemsId();
        this.quantity = addCartItemsRequest.quantity();
        this.productId = addCartItemsRequest.productId();
        this.cartId = addCartItemsRequest.cartId();
        this.price = addCartItemsRequest.price();
    }

    public static CartItems from(AddCartItemsRequest addCartItemsRequest){
        return new CartItems(addCartItemsRequest);
    }
}
