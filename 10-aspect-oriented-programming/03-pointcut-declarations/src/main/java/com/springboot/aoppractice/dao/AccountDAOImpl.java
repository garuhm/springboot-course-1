package com.springboot.aoppractice.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String name;

    @Override
    public void addAccount() {
        System.out.println("---adding acct operations---");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("---setting acct name operations---");
    }
}
