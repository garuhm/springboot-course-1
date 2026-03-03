package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {
    @Pointcut("execution(public * com.springboot.aoppractice.dao.*.*(..))")
    public void forDaoPackage(){};

    @Pointcut("execution(void com.springboot.aoppractice..*(..))")
    public void forVoidMethods(){};

    @Pointcut("execution(* com.springboot.aoppractice.dao.*.get*(..))")
    public void getters(){};

    @Pointcut("execution(* com.springboot.aoppractice.dao.*.set*(..))")
    public void setters(){};

    @Pointcut("forDaoPackage() && !(getters() || setters())")
    public void forDaoPackageNoGetSet(){};

    @Pointcut("forDaoPackage() && !(getters() || setters()) && !forVoidMethods()")
    public void forDaoPackageWithReturnNoGetSet(){};
}
