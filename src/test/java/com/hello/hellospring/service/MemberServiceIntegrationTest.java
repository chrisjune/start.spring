package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
//    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        Member member1 = new Member();
        member1.setName("spring");

        // when
        Long savedId = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void 중복_회원_검사() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        var e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //Then
    }
}
