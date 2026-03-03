package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(public void com.springboot.aoppractice.dao.MembershipDAO.add*())")
    public void beforeAddAccount(){
        System.out.println("## LOGGING: before advice for addAccount()");
    }
}
