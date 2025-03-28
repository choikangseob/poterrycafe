package ks.poterrycafe.service;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.UpdateProductRequest;
import ks.poterrycafe.dto.response.UpdateProductResponse;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.exception.UnauthorizedAccessException;
import ks.poterrycafe.repository.UpdateProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProductService {

    private final UpdateProductJPARepository updateProductJPARepository;


            public UpdateProductResponse updateProduct(UpdateProductRequest updateProductRequest, MemberDetails member) {

                if (member.getRole().isEmpty() || member.getRole().equals("ROLE_USER")) {
                    throw new UnauthorizedAccessException("관리자만 제품을 등록할 수 있습니다.");

                }
                Optional<Product> ProductOptional = updateProductJPARepository.findByproductIdAndCreateMemberId(updateProductRequest.productId(), member.getId());
                Product updateProduct = ProductOptional.orElseThrow(() -> new RuntimeException("결과값이 없습니다"));

                updateProduct.setProductName(updateProductRequest.productName());
                updateProduct.setDescription(updateProductRequest.description());
                updateProduct.setQuantity(updateProductRequest.quantity());
                updateProduct.setPrice(updateProductRequest.price());

                return UpdateProductResponse.of(updateProduct);
            }
}
