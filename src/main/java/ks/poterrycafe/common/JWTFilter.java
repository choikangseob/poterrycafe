package ks.poterrycafe.common;

import io.jsonwebtoken.ExpiredJwtException;
import ks.poterrycafe.entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Builder
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

// 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");



// 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {

            filterChain.doFilter(request, response);

            return;
        }

// 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

// 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

// username, role 값을 획득
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);
        long id = jwtUtil.getId(accessToken);

        System.out.println("Extracted Role from JWT: " + role);

        Member member = new Member();
        member.setUsername(username);
        member.setId(id);



            MemberDetails memberDetails = new MemberDetails(member, role);

            Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
















        /*       //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        String token = authorization.split(" ")[1];




       Member member = Member.builder()
               .username(jwtUtil.getUsername(token))
               .id(jwtUtil.getId(token))
               .build();

       if(member.getUsername().equals("admin")){

           String role = "ROLE_ADMIN";
           MemberDetails memberDetails = new MemberDetails(member,role);

           Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());

           SecurityContextHolder.getContext().setAuthentication(authToken);

           filterChain.doFilter(request, response);*/

       }
}
