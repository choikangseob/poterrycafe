package ks.poterrycafe.controller;


import ks.poterrycafe.dto.request.AddCartItemsRequest;
import ks.poterrycafe.dto.response.AddCartItemsResponse;
import ks.poterrycafe.service.AddCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AddCartItemController {

    private final AddCartItemService addCartItemService;

    @PostMapping("/addCartItem")
    public ResponseEntity<AddCartItemsResponse> addCartItem(

            @RequestParam long cartId,
            @RequestBody AddCartItemsRequest addCartItemsRequest
    ) {
        return ResponseEntity.ok(addCartItemService.addCartItem(cartId,addCartItemsRequest));
    }
}
