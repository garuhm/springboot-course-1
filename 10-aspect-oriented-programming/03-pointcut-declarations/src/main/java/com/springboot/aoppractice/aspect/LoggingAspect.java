package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.*(..))")
    private void forDaoPackage(){};

    @Before("forDaoPackage()")
    public void beforeAddAccount(){
        System.out.println("## LOGGING: before advice for addAccount()");
    }

    @Before("forDaoPackage()")
    public void performAPIAnalaytics(){
        System.out.println("## ANALYTICS: analyzing api analytics");
    }
}
