package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {
    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.*(..))")
    public void forDaoPackage(){};

    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.get*(..))")
    public void getters(){};

    @Pointcut("execution(public void com.springboot.aoppractice.dao.*.set*(..))")
    public void setters(){};

    @Pointcut("forDaoPackage() && !(getters() || setters())")
    public void forDaoPackageNoGetSet(){};
}
