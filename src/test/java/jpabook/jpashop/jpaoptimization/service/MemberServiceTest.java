package jpabook.jpashop.jpaoptimization.service;

import jpabook.jpashop.jpaoptimization.domain.Address;
import jpabook.jpashop.jpaoptimization.domain.Member;
import jpabook.jpashop.jpaoptimization.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // @Rollback(true)  // 기본값
    public void 회원가입() throws Exception {
        // given
        Address address = Address.builder()
                .city("Ssagmun").street("ojingst").zipcode("A123").build();
        Member member = Member.builder()
                .name("Junyoung").address(address).build();
        // when
        Long memberId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(memberId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Address address1 = Address.builder()
                .city("Ssagmun").street("ojingst").zipcode("A123").build();
        Member member1 = Member.builder()
                .name("Junyoung").address(address1).build();
        Address address2 = Address.builder()
                .city("Nowon").street("rojingst").zipcode("B123").build();
        Member member2 = Member.builder()
                .name("Junyoung").address(address2).build();
        // when
        memberService.join(member1);
        memberService.join(member2);    // 예외가 발생해야 함

        // then
        fail("예외가 발생해야 한다.");
    }
}