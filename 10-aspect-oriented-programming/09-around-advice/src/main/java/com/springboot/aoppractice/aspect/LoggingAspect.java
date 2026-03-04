package com.springboot.aoppractice.aspect;

import com.springboot.aoppractice.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class LoggingAspect {
    @Before("com.springboot.aoppractice.aspect.AOPExpressions.forAopPackage()")
    public void beforeMethodLogging(JoinPoint joinPoint){
        System.out.println("## LOGGING: before advice for dao");
        System.out.println("# method: " + joinPoint.getSignature());
        System.out.println("# args passed in: ");
        for(Object arg : joinPoint.getArgs()){
            System.out.println(arg);
        }
    }

    @AfterReturning(pointcut = "com.springboot.aoppractice.aspect.AOPExpressions.forAopPackage()", returning = "result")
    public void afterMethodReturnLogging(JoinPoint joinPoint, List<Account> result){
        System.out.println("## LOGGING: method call for " + joinPoint.getSignature().toShortString() + " was successful");
        System.out.println("# result: " + result);
    }
//
//    @AfterThrowing(pointcut = "com.springboot.aoppractice.aspect.AOPExpressions.forAopPackage()", throwing = "exc")
//    public void afterMethodExceptionLogging(JoinPoint joinPoint, Throwable exc){
//        System.out.println("## LOGGING: method call for " + joinPoint.getSignature().toShortString() + " failed, exception");
//        System.out.println("# exception: " + exc.getClass().getName() + " - " + exc.getMessage());
//    }

    @After("com.springboot.aoppractice.aspect.AOPExpressions.forAopPackage()")
    public void afterMethodLogging(JoinPoint joinPoint){
        System.out.println("## LOGGING: method call for " + joinPoint.getSignature().toShortString() + " concluded");
    }

    @Around("com.springboot.aoppractice.aspect.AOPExpressions.forAopPackage()")
    public Object aroundAdviceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
            Object result = null;

        try{
            result = proceedingJoinPoint.proceed();
        } catch(Exception e){
            System.out.println("## LOGGING: method call for " + proceedingJoinPoint.getSignature().toShortString() + " failed");
            System.out.println("# exception: " + e.getClass().getName() + " - " + e.getMessage());
        }

        long end = System.nanoTime();
        long duration = end - start;

        System.out.println("## LOGGING: time it took to execute " +  proceedingJoinPoint.getSignature() + ": " + duration / 10000000 + " ms");
        return result;
    }
}
