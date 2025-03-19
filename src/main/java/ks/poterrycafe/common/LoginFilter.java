package ks.poterrycafe.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.poterrycafe.entity.Refresh;
import ks.poterrycafe.repository.RefreshJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final RefreshJPARepository refreshJPARepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password,null);

        return authenticationManager.authenticate(authToken);
    }
    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        System.out.println("로그인 성공");

        MemberDetails username = (MemberDetails) authResult.getPrincipal();
        String refreshUsername = username.getUsername();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        String role = "ROLE_USER";  // 기본 권한 설정 (권한이 없을 때 사용)

// 권한이 있을 경우, 권한을 가져오고 기본값을 덮어씀
        if (authorities != null && !authorities.isEmpty()) {
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            if (iterator.hasNext()) {
                GrantedAuthority auth = iterator.next();
                role = auth.getAuthority();  // 실제 권한 값
            }
        } else {
            // 권한이 없으면 기본 권한 그대로 사용
            System.out.println("No authorities assigned to the user. Default role: " + role);
        }

// 이후 role을 사용하여 로직 처리
        System.out.println("User role: " + role);
        // String token = jwtUtil.createJwt(username);

        // response.addHeader("Authorization", "Bearer " + token);


        //토큰 생성
        String access = jwtUtil.createJwt("access", username,role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", username,role,86400000L);


        //Refresh 토큰 저장
        addRefreshEntity(refreshUsername, refresh, 86400000L);

        //응답 설정
        response.setHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());

    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        System.out.println("로그인 실패");
       response.setStatus(401);
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
