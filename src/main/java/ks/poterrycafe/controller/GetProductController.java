package ks.poterrycafe.controller;


import ks.poterrycafe.dto.response.SaveProductResponse;
import ks.poterrycafe.entity.Product;
import ks.poterrycafe.service.GetProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GetProductController {

    private final GetProductService getProductService;


    @PostMapping("/getProduct")
    public ResponseEntity<List<SaveProductResponse>> getProduct() {
        return ResponseEntity.ok(getProductService.getProducts());
    }
}
