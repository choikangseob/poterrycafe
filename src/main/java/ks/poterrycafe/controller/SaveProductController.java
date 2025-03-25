package ks.poterrycafe.controller;


import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.SaveProductRequest;
import ks.poterrycafe.dto.response.SaveProductResponse;
import ks.poterrycafe.entity.Member;
import ks.poterrycafe.service.SaveProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaveProductController {

    private final SaveProductService saveProductService;

    @PostMapping("/saveProduct")
    public ResponseEntity<SaveProductResponse> saveProduct(

            @RequestBody SaveProductRequest saveProductRequest,
            @AuthenticationPrincipal MemberDetails member
    ){

        return ResponseEntity.ok(saveProductService.saveProduct(saveProductRequest,member));
    }

}
