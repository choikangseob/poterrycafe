package ks.poterrycafe.common;

import ks.poterrycafe.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class MemberDetails implements UserDetails {

    private final Member member;
    private final String role;

    public MemberDetails(Member temporaryUsername2) {
        this.member = temporaryUsername2;
        this.role = "DEFAULT_ROLE";
    }

    public MemberDetails(Member member, String role) {
        this.member = member;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // role을 Spring Security에서 인식할 수 있도록 SimpleGrantedAuthority로 변환
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    public Long getId(){
        return member.getId();
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
