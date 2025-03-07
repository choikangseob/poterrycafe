package ks.poterrycafe.entity;

import ks.poterrycafe.dto.request.JoinMemberRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String email;

    private String password;


    public Member(JoinMemberRequest joinMemberRequest) {
        this.username = joinMemberRequest.username();
        this.email = joinMemberRequest.email();
        this.password = joinMemberRequest.password();
    }

    public static Member from(JoinMemberRequest joinMemberRequest) {

        return new Member(joinMemberRequest);
    }
}
