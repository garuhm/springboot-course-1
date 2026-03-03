package com.springboot.aoppractice.aspect;

import com.springboot.aoppractice.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @AfterReturning(pointcut = "com.springboot.aoppractice.aspect.AOPExpressions.forDaoPackageNoGetSet()", returning = "result")
    public void afterMethodReturnLogging(JoinPoint joinPoint, List<Account> result){
        System.out.println("## LOGGING: method call for " + joinPoint.getSignature().toShortString() + " was successful");
        System.out.println("# result: " + result);
    }
}
