package ks.poterrycafe.service;


import ks.poterrycafe.dto.request.AddCartItemsRequest;
import ks.poterrycafe.dto.response.AddCartItemsResponse;
import ks.poterrycafe.entity.CartItems;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.repository.AddCartItemsJPARepository;
import ks.poterrycafe.repository.SaveProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddCartItemService {

    private final AddCartItemsJPARepository addCartItemsJPARepository;

    private final SaveProductJPARepository saveProductJPARepository;

    public AddCartItemsResponse addCartItem(long cartId, AddCartItemsRequest addCartItemsRequest){

        Product product = saveProductJPARepository.findById(addCartItemsRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItems cartItems = CartItems.from(addCartItemsRequest);
        cartItems.setCartId(cartId);
        cartItems.setPrice(product.getPrice()* cartItems.getQuantity());

        CartItems addCartItems = addCartItemsJPARepository.save(cartItems);


        return AddCartItemsResponse.of(addCartItems);
    }
}
