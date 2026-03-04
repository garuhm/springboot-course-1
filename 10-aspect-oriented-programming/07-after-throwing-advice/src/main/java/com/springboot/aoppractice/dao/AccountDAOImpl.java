package com.springboot.aoppractice.dao;

import com.springboot.aoppractice.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private List<Account> accounts;

    public AccountDAOImpl() {
    }

    @Autowired
    public AccountDAOImpl(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void addAccount(Account account) {
        System.out.println("---adding acct operations---");
        accounts.add(account);
    }

    @Override
    public List<Account> findAccounts(boolean flag) {
        System.out.println("--listing acct operations--");
        if(flag){
            throw new RuntimeException("simulated exception!");
        }
        return accounts;
    }
}
