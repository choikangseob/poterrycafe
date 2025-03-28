package ks.poterrycafe.service;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.SaveProductRequest;
import ks.poterrycafe.dto.response.SaveProductResponse;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.exception.UnauthorizedAccessException;
import ks.poterrycafe.repository.SaveProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveProductService {

    private final SaveProductJPARepository saveProductJPARepository;

    public SaveProductResponse saveProduct(SaveProductRequest saveProductRequest, MemberDetails member) {


        if (member.getRole().isEmpty() || member.getRole().equals("ROLE_USER")) {
            throw new UnauthorizedAccessException("관리자만 제품을 등록할 수 있습니다.");

        }
            Product saveProduct = Product.from(saveProductRequest);

            Product savedProduct = saveProductJPARepository.save(saveProduct);


            return SaveProductResponse.of(savedProduct);
    }
}
