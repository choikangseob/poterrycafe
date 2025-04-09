package ks.poterrycafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ks.poterrycafe.common.entity.EssentialColumns;
import ks.poterrycafe.dto.request.CreateCartRequest;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends EssentialColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long cartId;

    public Cart(CreateCartRequest createCartRequest) {
        this.cartId = createCartRequest.cartId();
    }

    public static Cart from(CreateCartRequest createCartRequest) {

        return new Cart(createCartRequest);
    }
}
