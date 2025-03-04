package gs.poterrycafe.service;


import gs.poterrycafe.dto.request.JoinMemberRequest;
import gs.poterrycafe.dto.response.MemberResponse;
import gs.poterrycafe.entity.Member;
import gs.poterrycafe.repository.MemberJPARepository;
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
