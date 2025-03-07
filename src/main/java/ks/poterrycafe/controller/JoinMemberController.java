package ks.poterrycafe.controller;

import ks.poterrycafe.dto.request.JoinMemberRequest;
import ks.poterrycafe.dto.response.MemberResponse;
import ks.poterrycafe.service.JoinMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinMemberController {

    private final JoinMemberService joinMemberService;

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> joinMember(

            @RequestBody JoinMemberRequest joinMemberRequest
            ){
        return ResponseEntity.ok(joinMemberService.joinMember(joinMemberRequest));
    }
}
