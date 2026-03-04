package com.springboot.aoppractice;

import com.springboot.aoppractice.dao.AccountDAO;
import com.springboot.aoppractice.dao.AccountDAOImpl;
import com.springboot.aoppractice.dao.MembershipDAO;
import com.springboot.aoppractice.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
        return runner -> {
            demoTheAdvice(accountDAO);
        };
    }

    private void demoTheAdvice(AccountDAO accountDAO) {
        List<Account> accountList = null;

        try{
            boolean tripWire = true;
            accountDAO.addAccount(new Account("gabe", "1"));
            accountList = accountDAO.findAccounts(tripWire);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(accountList);
//        accountDAO.setName("yeah");
//        membershipDAO.addAccount(new Account("jackelyn", "1"));

    }

}
