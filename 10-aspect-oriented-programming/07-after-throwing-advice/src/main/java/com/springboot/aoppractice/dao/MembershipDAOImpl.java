package com.springboot.aoppractice.dao;

import com.springboot.aoppractice.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements  MembershipDAO{
    @Override
    public void addAccount(Account account) {
        System.out.println("---adding acct membership operations---");
    }
}
