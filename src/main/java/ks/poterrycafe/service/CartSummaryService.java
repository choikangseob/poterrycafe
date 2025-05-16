package ks.poterrycafe.service;

import jakarta.transaction.Transactional;
import ks.poterrycafe.dto.response.CartSummaryResponse;
import ks.poterrycafe.dto.response.GetCartItemsResponse;
import ks.poterrycafe.repository.CartSummaryMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartSummaryService {


    private final CartSummaryMybatisRepository cartSummaryMybatisRepository;

    public List<CartSummaryResponse> cartSummary(int cartId) {

        int count = cartSummaryMybatisRepository.findByCartIdCount(cartId);
        int summaryCount = cartSummaryMybatisRepository.findByCartIdOfSummaryCount(cartId);

        if(count == 0 && summaryCount > 0){

            cartSummaryMybatisRepository.deleteCartSummary(cartId);

            return Collections.emptyList();
        }

        if (count == 0) {
            cartSummaryMybatisRepository.insertCartSummary(cartId);
        } else {
            cartSummaryMybatisRepository.updateCartSummary(cartId);
        }

        return cartSummaryMybatisRepository.findByCartId(cartId);
    }
}
