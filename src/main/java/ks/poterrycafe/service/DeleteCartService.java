package ks.poterrycafe.service;

import jakarta.transaction.Transactional;
import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.DeleteCartRequest;
import ks.poterrycafe.dto.response.DeleteCartResponse;
import ks.poterrycafe.entity.Cart;
import ks.poterrycafe.repository.DeleteCartJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteCartService {

    private final DeleteCartJPARepository deleteCartJPARepository;

    public DeleteCartResponse deleteCart(DeleteCartRequest deleteCartRequest, MemberDetails member){

        Optional<Cart> cartOptional = deleteCartJPARepository.findByCartIdAndCreateMemberId(deleteCartRequest.cartId(), member.getId());

        Cart cartTobeDelete = cartOptional.orElseThrow(() -> new RuntimeException("결과값이 없습니다"));

        deleteCartJPARepository.delete(cartTobeDelete);
        return DeleteCartResponse.of(cartTobeDelete);
    }
}
