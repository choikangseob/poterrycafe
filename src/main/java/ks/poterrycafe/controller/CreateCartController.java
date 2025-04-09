package ks.poterrycafe.controller;

import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.dto.request.CreateCartRequest;
import ks.poterrycafe.dto.response.CreateCartResponse;
import ks.poterrycafe.service.CreateCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CreateCartController {

    private final CreateCartService createCartService;


    @PostMapping("/createCart")
    public ResponseEntity<CreateCartResponse> createCart(

            @RequestBody CreateCartRequest createCartRequest,
            @AuthenticationPrincipal MemberDetails member
            ){
     return ResponseEntity.ok(createCartService.createCart(createCartRequest,member));
    }
}
