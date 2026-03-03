package com.springboot.aoppractice.aspect;

import com.springboot.aoppractice.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class ModifyResultAspect {
    @AfterReturning(pointcut = "execution(java.util.List<com.springboot.aoppractice.entity.Account> com.springboot.aoppractice.dao.AccountDAO.*(..))",
                    returning = "result")
    public void modifyFirstUser(JoinPoint joinPoint, List<Account> result){
        if(result != null) {
            result.get(0).setLevel(Integer.toString(Integer.parseInt(result.get(0).getLevel()) + 1));
        }
    }
}
