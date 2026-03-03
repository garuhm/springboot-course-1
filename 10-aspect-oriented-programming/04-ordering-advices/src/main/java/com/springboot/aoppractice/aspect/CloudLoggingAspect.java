package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLoggingAspect {
    @Before("com.springboot.aoppractice.aspect.AOPExpressions.forDaoPackageNoGetSet()")
    public void beforeMethodCloudLogging(){
        System.out.println("## CLOUD LOGGING: before advice for dao");
    }
}
