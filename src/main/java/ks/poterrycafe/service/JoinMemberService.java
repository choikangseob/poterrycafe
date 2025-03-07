package ks.poterrycafe.service;


import ks.poterrycafe.dto.request.JoinMemberRequest;
import ks.poterrycafe.entity.Member;
import ks.poterrycafe.dto.response.MemberResponse;
import ks.poterrycafe.repository.MemberJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinMemberService {
    private final MemberJPARepository memberJPARepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public MemberResponse joinMember(JoinMemberRequest joinMemberRequest){

        Member joinMember  = Member.from(joinMemberRequest);
        joinMember.setPassword(
                bCryptPasswordEncoder.encode(joinMember.getPassword())
        );

        Member savedMember = memberJPARepository.save(joinMember);

        return MemberResponse.of(savedMember);
    }
}
