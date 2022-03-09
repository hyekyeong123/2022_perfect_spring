package com.codingcat.perfect.repository;

import com.codingcat.perfect.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 통합 테스트
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){

        // 하나의 테스트가 끝날때마다 메모리 정리
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> memberList = repository.findAll();
        assertThat(memberList.size()).isEqualTo(2);
    }
}