package ks.poterrycafe.controller;


import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.UpdateProductRequest;
import ks.poterrycafe.dto.response.UpdateProductResponse;
import ks.poterrycafe.service.UpdateProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @PostMapping("/updateProduct")
    public ResponseEntity<UpdateProductResponse> updateProduct(

            @RequestBody UpdateProductRequest updateProductRequest,
            @AuthenticationPrincipal MemberDetails member
    ){
        return ResponseEntity.ok(updateProductService.updateProduct(updateProductRequest,member));
    }
}
