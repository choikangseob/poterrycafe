package ks.poterrycafe.service;


import ks.poterrycafe.dto.request.DeleteCartItemsRequest;
import ks.poterrycafe.dto.response.DeleteCartItemsResponse;
import ks.poterrycafe.entity.CartItems;
import ks.poterrycafe.repository.DeleteCartItemsJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteCartItemsService {

    private final DeleteCartItemsJPARepository deleteCartItemsJPARepositoty;

    public DeleteCartItemsResponse deleteCartItems(DeleteCartItemsRequest deleteCartItemsRequest){

         Optional<CartItems> cartItemsOptional = deleteCartItemsJPARepositoty.findByCartItemsId(deleteCartItemsRequest.cartItemsId());

        CartItems cartItemsToBeDelete = cartItemsOptional.orElseThrow(() -> new RuntimeException("결과값이 없습니다."));

        deleteCartItemsJPARepositoty.delete(cartItemsToBeDelete);

        return DeleteCartItemsResponse.of(cartItemsToBeDelete);

    }
}
