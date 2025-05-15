package ks.poterrycafe.service;

import jakarta.transaction.Transactional;
import ks.poterrycafe.dto.response.CartSummaryResponse;
import ks.poterrycafe.dto.response.GetCartItemsResponse;
import ks.poterrycafe.repository.CartSummaryMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartSummaryService {


    private final CartSummaryMybatisRepository cartSummaryMybatisRepository;

    public List<CartSummaryResponse> cartSummary(int cartId){

        return cartSummaryMybatisRepository.findByCartId(cartId);
    }
}
