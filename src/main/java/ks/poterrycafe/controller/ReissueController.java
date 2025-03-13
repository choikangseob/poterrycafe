package ks.poterrycafe.controller;


import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.poterrycafe.common.JWTUtil;
import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.entity.Member;
import ks.poterrycafe.repository.MemberJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;

    private final MemberJPARepository memberJPARepository;

    public ReissueController(JWTUtil jwtUtil, MemberJPARepository memberJPARepository) {

        this.jwtUtil = jwtUtil;
        this.memberJPARepository = memberJPARepository;
    }


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        String temporaryUsername = jwtUtil.getUsername(refresh);  // username을 얻음

// Member 객체를 데이터베이스에서 찾기
        Optional<Member> member = memberJPARepository.findByUsername(temporaryUsername);  // 가정: MemberRepository가 있음

        Member temporaryUsername2 = null;
        if(member.isPresent()) {
            temporaryUsername2 = member.get();
        }
// MemberDetails 객체를 생성
            MemberDetails username = new MemberDetails(temporaryUsername2);


            String role = jwtUtil.getRole(refresh);

            //make new JWT
            String newAccess = jwtUtil.createJwt("access", username, role, 600000L);

            //response
            response.setHeader("access", newAccess);


        return new ResponseEntity<>(HttpStatus.OK);
    }


}
