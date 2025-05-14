package ks.poterrycafe.service;

import jakarta.transaction.Transactional;
import ks.poterrycafe.dto.response.AddCartItemsResponse;
import ks.poterrycafe.dto.response.GetCartItemsResponse;
import ks.poterrycafe.repository.GetCartItemsMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetCartItemsService {

    private final GetCartItemsMybatisRepository getCartItemsMybatisRepository;


    public List<GetCartItemsResponse> getCartItems(int cartId, int productId) {

        return getCartItemsMybatisRepository.findByCartIdAndProductId(cartId,productId);

    }
}
