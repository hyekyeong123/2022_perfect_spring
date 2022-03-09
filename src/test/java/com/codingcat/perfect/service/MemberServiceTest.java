package com.codingcat.perfect.service;

import com.codingcat.perfect.domain.Member;
import com.codingcat.perfect.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 좋은 테스트는 단위 테스트
class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memory;

    // 이렇게 하면 같은 memory를 공유함
    @BeforeEach
    public void beforeEach(){
        memory = new MemoryMemberRepository();
        memberService = new MemberService(memory);
    }

    @AfterEach
    public void afterEach(){
        memory.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

         // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member).isEqualTo(findMember);

    }

    @Test
    public void 중복_회원_검증(){
        // given
        Member member = new Member();
        member.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member);

        // 일부러 에러를 내뱉게 해서
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> memberService.join(member2));

        // 에러 메시지와 비교
         assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
/*
    assertThrows(예상하는 예외 타입, 예외가 터질 것으로 예상하는 코드)
*/
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}