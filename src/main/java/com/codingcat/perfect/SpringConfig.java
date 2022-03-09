package com.codingcat.perfect;

import com.codingcat.perfect.aop.TimeTraceAop;
import com.codingcat.perfect.repository.*;
import com.codingcat.perfect.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

    // ****************** 순수 JPA ***************************
    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    // ****************** Spring Data JPA ***************************
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // 나중에 DB가 변경된다면 이 부분만 변경해주면 됨(기존 source 변경 X) Spring Data Jpa는 윗 부분 참고
/*    @Bean
    public MemberRepository memberRepository(){

        // 인메모리 방식
        // return new MemoryMemberRepository();

        // 순수 JDBC 방식
        // return new JdbcMemberRepository(dataSource);

        // JDBC 템플릿 방식
        // return new JdbcTemplateMemberRepository(dataSource);

        // JPA 방식
        return new JpaMemberRepository(em);
    }*/

//    *****************************************************************

    // @Component로 대체 가능
/*    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/
}
