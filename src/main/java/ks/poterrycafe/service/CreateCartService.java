package ks.poterrycafe.service;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.CreateCartRequest;
import ks.poterrycafe.dto.response.CreateCartResponse;
import ks.poterrycafe.entity.Cart;
import ks.poterrycafe.repository.CreateCartJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateCartService {

    private final CreateCartJPARepository createCartJPARepository;

    public CreateCartResponse createCart(CreateCartRequest createCartRequest, MemberDetails member) {

        Cart createCart = Cart.from(createCartRequest);

        Cart createdCart = createCartJPARepository.save(createCart);


        return CreateCartResponse.of(createdCart);
    }
}
