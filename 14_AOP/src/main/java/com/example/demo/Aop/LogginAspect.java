package com.example.demo.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogginAspect {

    //execution(* com.example.demo.Model.Service.MemoService.addMemo(..))
    //target : MemoService.addMemo 함수
    //execution(* com.example.demo.Model.Service.MemoService.*(..))
    //MemoService안의 모든 Method
    //execution(* com.example.demo.Model.Service.*.*(..))
    //Service패키지안의 모든 Service안의 모든  Method


//    @Before("execution(* com.example.demo.Domain.Common.Service.AopTestService.run1(..))")
//    @Before("execution(* com.example.demo.Domain.Common.Service.AopTestService.*(..))")
//    @Before("execution(* com.example.demo.Domain.Common.Service.*.*(..))")
//    public void logginBefore(JoinPoint joinPoint){
//        log.info("[AOP] BEFORE..." + joinPoint);
//    }

    //    @After("execution(* com.example.demo.Domain.Common.Service.AopTestService.run1(..))")
//    @After("execution(* com.example.demo.Domain.Common.Service.AopTestService.*(..))")
    @After("execution(* com.example.demo.Domain.Common.Service.*.*(..))")
    public void loggingAfter(JoinPoint joinPoint){
        log.info("[AOP] AFTER..." + joinPoint);
    }
}
