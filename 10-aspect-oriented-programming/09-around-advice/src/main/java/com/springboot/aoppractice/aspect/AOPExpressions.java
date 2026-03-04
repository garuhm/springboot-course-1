package com.springboot.aoppractice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {

    @Pointcut("execution(* org.springframework.boot.CommandLineRunner.run(..))")
    public void commandLineRunner(){}

    @Pointcut("execution(* com.springboot.aoppractice..*(..))")
    public void aopPackage(){};

    @Pointcut("!commandLineRunner() && aopPackage()")
    public void forAopPackage(){};

    @Pointcut("execution(public * com.springboot.aoppractice.dao.*.*(..))")
    public void daoPackage(){};

    @Pointcut("!commandLineRunner() && daoPackage()")
    public void forDAOPackage(){};

    @Pointcut("execution(public * com.springboot.aoppractice.service.*.*(..))")
    public void servicePackage(){};

    @Pointcut("execution(void com.springboot.aoppractice..*(..))")
    public void voidMethods(){};

    @Pointcut("execution(* com.springboot.aoppractice.dao.*.get*(..))")
    public void daoGetters(){};

    @Pointcut("execution(* com.springboot.aoppractice.dao.*.set*(..))")
    public void daoSetters(){};

    @Pointcut("forDAOPackage() && !(daoGetters() || daoSetters())")
    public void forDaoPackageNoGetSet(){};

    @Pointcut("forDAOPackage() && !(daoGetters() || daoSetters()) && !voidMethods()")
    public void forDaoPackageWithReturnNoGetSet(){};
}
