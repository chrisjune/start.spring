package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        // when
        Long savedId = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(findMember).isEqualTo(member1);
    }
    @Test
    void 중복조회() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member(); 
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //then
    }
}