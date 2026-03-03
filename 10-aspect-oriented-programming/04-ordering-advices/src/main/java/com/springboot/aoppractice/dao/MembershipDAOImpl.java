package com.springboot.aoppractice.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements  MembershipDAO{
    @Override
    public void addAccount(String s) {
        System.out.println("---adding acct membership operations---");
    }
}
