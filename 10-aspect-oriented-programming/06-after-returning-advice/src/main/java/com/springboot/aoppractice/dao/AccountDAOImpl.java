package com.springboot.aoppractice.dao;

import com.springboot.aoppractice.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String name;

    @Override
    public void addAccount(Account account) {
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
