package ks.poterrycafe.controller;


import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.poterrycafe.common.JWTUtil;
import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.entity.Member;
import ks.poterrycafe.entity.Refresh;
import ks.poterrycafe.repository.MemberJPARepository;
import ks.poterrycafe.repository.RefreshJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

@Controller
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;

    private final MemberJPARepository memberJPARepository;

    private final RefreshJPARepository refreshJPARepository;

    public ReissueController(JWTUtil jwtUtil, MemberJPARepository memberJPARepository, RefreshJPARepository refreshJPARepository) {

        this.jwtUtil = jwtUtil;
        this.memberJPARepository = memberJPARepository;
        this.refreshJPARepository = refreshJPARepository;
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
        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshJPARepository.existsByRefresh(refresh);
        if (!isExist) {

            //response body
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
            String refreshUsername = username.getUsername();

            String role = jwtUtil.getRole(refresh);

            //make new JWT
            String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
            String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshJPARepository.deleteByRefresh(refresh);
        addRefreshEntity(refreshUsername, newRefresh, 86400000L);

            //response
            response.setHeader("access", newAccess);
            response.addCookie(createCookie("refresh", newRefresh));


        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = new Refresh();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshJPARepository.save(refreshEntity);
    }
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

}
