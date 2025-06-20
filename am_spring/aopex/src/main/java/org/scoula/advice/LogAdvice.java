package org.scoula.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect  //AOP Aspect 임을 선언
@Log4j2
@Component
public class LogAdvice {

    /*
    어떤 타겟이 되는 클래스의 메서드를 호출해서 실행할 때
    어떤 부가적인 기능을 넣을지 충고해주는 클래스
    ==> 클래스 이름위에 @Aspect 라고 해주어야함
     */

    //SampleService로 시작하는 모든 클래스의 모든 메서드 실행 전에 동작 = @Before
    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("==================================");
    }

    /*
    부가적인 기능을 넣을 타겟이 되는 SampleService 클래스 안에 있는
    조인 포인트가 되는 모든 메서드를 호출해서 실행하기 전에 (이벤트라고 인식함)
    logBefore()메서드를 먼저 호출해라! 설정
    => 이벤트가 발생하면 AOP 프락시 객체가 logBfore()메서드를 먼저 호출함
    스프링에서 사용된 설계 기법 3가지 => 24~26 정도로 설계 패턴을 정리
    프락시(대리인) 설계 패턴 (design pattern)
    싱글톤
    팩토리
     */

    //args를 이용한 파라미터 추적
    //해당 메서드에 전달되는 파라미터가 무엇인지 기록하건,
    //예외가 발생했을 때 어떤 파라미터에 문제가 있는지 알고 싶은 경우
    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }

    //예외 처리
    //@AfterThrowing : 지정된 대상이 예외를 발생한 후에 동작
    @AfterThrowing(pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))", throwing="exception")
    public void logException(Exception exception) {
        log.info("Exception...!!!!");
        log.info("exception: " + exception);
    }

    //@Around 와 ProceedingJoinPoint
    //메서드의 실행 전과 실행 후에 처리가 가능
    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        Object result = null;
        try {
            result = pjp.proceed(); // 실제 메서드 호출
        } catch(Throwable e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        log.info("TIME: " + (end - start));

        return result;
    }

}