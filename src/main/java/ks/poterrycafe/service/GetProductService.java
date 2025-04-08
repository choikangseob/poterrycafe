package ks.poterrycafe.service;


import ks.poterrycafe.dto.response.SaveProductResponse;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.repository.GetProductMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductService {

    private final GetProductMybatisRepository getProductMybatisRepository;


    public List<SaveProductResponse> getProducts() {

        return getProductMybatisRepository.findProductIdAndProductNameAndDescriptionAndQuantityAndPrice();
    }
}
