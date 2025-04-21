package ks.poterrycafe.service;


import ks.poterrycafe.dto.request.UpdateCartItemsRequest;
import ks.poterrycafe.dto.response.UpdateCartItemsResponse;
import ks.poterrycafe.entity.CartItems;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.repository.SaveProductJPARepository;
import ks.poterrycafe.repository.UpdateCartItemsJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateCartItemsService {

    private final UpdateCartItemsJPARepository updateCartItemsJPARepository;

    private final SaveProductJPARepository saveProductJPARepository;

    public UpdateCartItemsResponse updateCartItems(UpdateCartItemsRequest updateCartItemsRequest){



        Optional<CartItems> cartItemsOptional = updateCartItemsJPARepository.findBycartItemsId(updateCartItemsRequest.cartItemsId());
        CartItems updateCartItems = cartItemsOptional.orElseThrow(() -> new RuntimeException("CartItems not found"));


        Product product = saveProductJPARepository.findById(updateCartItemsRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        updateCartItems.setCartId(updateCartItemsRequest.cartId());
        updateCartItems.setProductId(updateCartItemsRequest.productId());
        updateCartItems.setQuantity(updateCartItemsRequest.quantity());
        updateCartItems.setPrice(updateCartItems.getQuantity()*product.getPrice());


        return UpdateCartItemsResponse.of(updateCartItems);
    }
}
