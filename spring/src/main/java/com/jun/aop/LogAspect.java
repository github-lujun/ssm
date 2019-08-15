package com.jun.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.jun.service.impl.*.*(..))")
    public void before(){
        System.out.println("hello");
    }
}
