package gs.poterrycafe.controller;

import gs.poterrycafe.entity.Member;
import gs.poterrycafe.repository.MemberJPARepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase
class JoinMemberControllerTest {

    @Autowired
    private MemberJPARepository memberJPARepository;


    @Test
    @DisplayName("회원가입이 완료 되었는지 테스트")
    public void save (){
        // given

        Member member = Member.builder()
                .username("maple")
                .password("123123")
                .build();
        // when
    Member savedMember = memberJPARepository.save(member);
        // then
        assertThat(savedMember).isNotNull();

        assertSoftly(softly ->{
            softly.assertThat(savedMember.getUsername()).isEqualTo("maple");
            softly.assertThat(savedMember.getPassword()).isEqualTo("123123");
        });
    }

}