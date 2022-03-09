package com.codingcat.perfect.service;

import com.codingcat.perfect.domain.Member;
import com.codingcat.perfect.repository.MemberRepository;
import com.codingcat.perfect.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 DB까지 연결한 통합 테스트

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional
/*테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지않는다*/
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memory;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

         // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

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
}