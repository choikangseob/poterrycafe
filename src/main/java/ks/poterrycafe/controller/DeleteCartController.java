package ks.poterrycafe.controller;


import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.DeleteCartRequest;
import ks.poterrycafe.dto.response.DeleteCartResponse;
import ks.poterrycafe.service.DeleteCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class DeleteCartController {

    private final DeleteCartService deleteCartService;

    @PostMapping("/deleteCart")
    public ResponseEntity<DeleteCartResponse> deleteCart(

            @RequestBody DeleteCartRequest deleteCartRequest,
            @AuthenticationPrincipal MemberDetails member
    ){

        return ResponseEntity.ok(deleteCartService.deleteCart(deleteCartRequest,member));
    }
}
