package ks.poterrycafe.controller;

import ks.poterrycafe.dto.response.AddCartItemsResponse;
import ks.poterrycafe.dto.response.GetCartItemsResponse;
import ks.poterrycafe.service.GetCartItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GetCartItemsController {

    private final GetCartItemsService getCartItemsService;


    @PostMapping("/getCartItems/{cartId}/{productId}")
    public ResponseEntity<List<GetCartItemsResponse>> getCartItems(
            @PathVariable int cartId,
            @PathVariable int productId
    ) {
        return ResponseEntity.ok(getCartItemsService.getCartItems(cartId,productId));


    }
}
