package ks.poterrycafe.controller;


import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.DeleteProductRequest;
import ks.poterrycafe.dto.response.DeleteProductResponse;
import ks.poterrycafe.service.DeleteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class DeleteProductController {

    private final DeleteProductService deleteProductService;

    @PostMapping("/deleteProduct")
    public ResponseEntity<DeleteProductResponse> deleteProduct(
            @RequestBody DeleteProductRequest deleteProductRequest,
            @AuthenticationPrincipal MemberDetails member
    ){

        return ResponseEntity.ok(deleteProductService.deleteProduct(deleteProductRequest,member));
    }
}
