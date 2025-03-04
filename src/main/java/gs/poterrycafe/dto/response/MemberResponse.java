package gs.poterrycafe.dto.response;


import gs.poterrycafe.dto.request.JoinMemberRequest;
import gs.poterrycafe.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long id,
        String username,
        String email,
        String password
) {
    public static MemberResponse of(Member savedMember){
        return MemberResponse.builder()
                .id(savedMember.getId())
                .username(savedMember.getUsername())
                .email(savedMember.getEmail())
                .password(savedMember.getPassword())
                .build();
    }
}
