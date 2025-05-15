package ks.poterrycafe.controller;

import ks.poterrycafe.dto.response.CartSummaryResponse;
import ks.poterrycafe.service.CartSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartSummaryController {

    private final CartSummaryService cartSummaryService;

    @PostMapping("/cartSummary/{cartId}")
    public ResponseEntity<List<CartSummaryResponse>> cartSummary(
            @PathVariable int cartId


    ){

        return ResponseEntity.ok(cartSummaryService.cartSummary(cartId));
    }
}
