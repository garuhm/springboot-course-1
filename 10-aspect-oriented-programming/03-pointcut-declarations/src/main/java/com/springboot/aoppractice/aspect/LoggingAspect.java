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

    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.get*(..))")
    private void getters(){};

    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.set*(..))")
    private void setters(){};

    @Pointcut("forDaoPackage() && !(getters() || setters())")
    private void forDaoPackageNoGetSet(){};

    @Before("forDaoPackageNoGetSet()")
    public void beforeAddAccount(){
        System.out.println("## LOGGING: before advice for addAccount()");
    }

    @Before("forDaoPackageNoGetSet()")
    public void performAPIAnalaytics(){
        System.out.println("## ANALYTICS: analyzing api analytics");
    }
}
