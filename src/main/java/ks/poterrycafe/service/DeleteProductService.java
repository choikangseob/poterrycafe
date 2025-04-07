package ks.poterrycafe.service;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.DeleteProductRequest;
import ks.poterrycafe.dto.response.DeleteProductResponse;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.exception.UnauthorizedAccessException;
import ks.poterrycafe.repository.DeleteProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteProductService {

    private final DeleteProductJPARepository deleteProductJPARepository;


    public DeleteProductResponse deleteProduct(DeleteProductRequest deleteProductrequest, MemberDetails member) {


        if (member.getRole().isEmpty() || member.getRole().equals("ROLE_USER")) {
            throw new UnauthorizedAccessException("관리자만 제품을 등록할 수 있습니다.");

        }

        Optional<Product> productOptional = deleteProductJPARepository.findByproductIdAndCreateMemberId(deleteProductrequest.productId(), member.getId());
        Product productTobeDelete = productOptional.orElseThrow(() -> new RuntimeException("결과값이 없습니다"));
        deleteProductJPARepository.delete(productTobeDelete);

        return DeleteProductResponse.of(productTobeDelete);
    }



}
