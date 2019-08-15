package com.jun.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.AjAttribute;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Before("execution(* com.jun.service.impl.*.*(..))")
    public void MyBefore(){
        System.out.println("before:hello");
    }
}
