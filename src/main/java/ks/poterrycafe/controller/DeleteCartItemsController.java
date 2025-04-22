package ks.poterrycafe.controller;


import ks.poterrycafe.dto.request.DeleteCartItemsRequest;
import ks.poterrycafe.dto.response.DeleteCartItemsResponse;
import ks.poterrycafe.service.DeleteCartItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DeleteCartItemsController {

    private final DeleteCartItemsService deleteCartItemsService;

    @PostMapping("/deleteCartItems")
    public ResponseEntity<DeleteCartItemsResponse> deleteCartItems(

            @RequestBody DeleteCartItemsRequest deleteCartItemsRequest
    ) {

        return ResponseEntity.ok(deleteCartItemsService.deleteCartItems(deleteCartItemsRequest));
    }
}
