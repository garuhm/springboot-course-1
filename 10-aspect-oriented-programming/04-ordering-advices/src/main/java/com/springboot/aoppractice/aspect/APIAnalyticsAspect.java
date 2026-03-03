package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class APIAnalyticsAspect {
    @Before("com.springboot.aoppractice.aspect.AOPExpressions.forDaoPackageNoGetSet()")
    public void beforeMethodAPIAnalytics(){
        System.out.println("## ANALYTICS: analyzing api analytics for dao");
    }
}
