package com.codingcat.perfect.service;

import com.codingcat.perfect.domain.Member;
import com.codingcat.perfect.repository.MemberRepository;
import com.codingcat.perfect.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
/*스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을
        커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
        JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.*/
//@Service
public class MemberService {

    // 같은 인스턴스를 사용하게 만들려면 new를 사용해서 의존성을 주입해서는 안된다.
    private final MemberRepository memberRepository;

    // 외부에서 직접 넣어줌(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입(같은 이름 X)
    public Long join(Member member){
        memberRepository.findByName(member.getName())

        .ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        memberRepository.save(member);
        return member.getId();
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 아이디로 회원 조회
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
