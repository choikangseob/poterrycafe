package ks.poterrycafe.service;




import ks.poterrycafe.common.MemberDetails;
import ks.poterrycafe.entity.Member;
import ks.poterrycafe.repository.MemberJPARepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberDetailsService implements UserDetailsService {


    private final MemberJPARepository memberJPARepository;

    public MemberDetailsService(MemberJPARepository memberJPARepository) {

        this.memberJPARepository = memberJPARepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> memberData = memberJPARepository.findRoleByUsername(username);

        if(memberData.isPresent()) {
            Member member = memberData.get();

            // username이 "admin"이면 ROLE_ADMIN, 아니면 ROLE_USER 부여
            String role = member.getRole();

            return new MemberDetails(member, role);
        }
         return null;
    }
}
