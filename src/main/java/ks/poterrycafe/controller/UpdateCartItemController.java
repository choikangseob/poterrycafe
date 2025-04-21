package ks.poterrycafe.controller;

import ks.poterrycafe.dto.request.UpdateCartItemsRequest;
import ks.poterrycafe.dto.response.UpdateCartItemsResponse;
import ks.poterrycafe.service.UpdateCartItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UpdateCartItemController {

    private final UpdateCartItemsService updateCartItemService;

    @PostMapping("/updateCartItems")
    public ResponseEntity<UpdateCartItemsResponse> updateCartItems(
            @RequestBody UpdateCartItemsRequest updateCartItemsRequest
    ){

        return ResponseEntity.ok(updateCartItemService.updateCartItems(updateCartItemsRequest));
    }
}
