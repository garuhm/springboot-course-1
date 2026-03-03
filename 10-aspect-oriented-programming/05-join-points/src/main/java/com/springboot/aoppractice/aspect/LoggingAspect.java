package com.springboot.aoppractice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {
    @Before("com.springboot.aoppractice.aspect.AOPExpressions.forDaoPackageNoGetSet()")
    public void beforeMethodLogging(JoinPoint joinPoint){
        System.out.println("## LOGGING: before advice for dao");
        System.out.println("# method: " + joinPoint.getSignature());
        System.out.println("# args passed in: ");
        for(Object arg : joinPoint.getArgs()){
            System.out.println(arg);
        }
    }
}
