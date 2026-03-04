package com.springboot.aoppractice.dao;

import com.springboot.aoppractice.entity.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account account);
     List<Account> findAccounts(boolean flag);
}
