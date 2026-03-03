package com.springboot.aoppractice.dao;

import com.springboot.aoppractice.entity.Account;

public interface AccountDAO {
    void addAccount(Account account);
     String getName();

     void setName(String name);
}
