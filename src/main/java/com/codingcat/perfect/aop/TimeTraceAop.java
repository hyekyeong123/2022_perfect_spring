package com.codingcat.perfect.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.naming.CannotProceedException;

@Component
@Aspect
public class TimeTraceAop {

    // 모든 메서드의 실행 시간 구하기
    @Around("execution(* com.codingcat.perfect..*(..))") // 원하는 대상 선택 가능
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs +
                    "ms");
        }
    }
}
