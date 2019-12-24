package com.spring.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //@Around("execution(* com.spring.springaop.service.BookService.*(..))")
    //*@Around("execution(* com.spring.springaop.controller..*.*(..))")//
    @Around("execution(* com.spring.springaop..*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        logger.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " +pjp.getSignature().getName());
        return result;
    }

//    classic spring proxy 기반 AOP를 이용해 Dao에서 Service를, Service에서 Controller를 호출하지 못하도록 막는 용도로 사용가능
//    하위 계층이 상위 계층을 호출 하면 안되므로
//    classic spring proxy 기반 AOP는 무겁고 복잡하다라는 단점이 있다.
//
//    @Pointcut("execution(* com.spring.springaop.repository..*(..))")
//    public void executeDao(){}
//
//    @Pointcut("call(* com.spring.springaop.service..*(..))")
//    public void callToService(){}
//
//    @Before("cflowbelow(executeDao()) && callToService()")
//    public void checkDao(JoinPoint jp){
//        logger.info("daoToService architecture checking");
//        throw new RuntimeException("Dao can't call Service's method.");
//    }

}
