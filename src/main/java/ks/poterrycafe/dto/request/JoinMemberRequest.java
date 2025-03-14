package ks.poterrycafe.dto.request;



public record JoinMemberRequest(
        String username,
        String email,
        String password,
        String role
) {
}
