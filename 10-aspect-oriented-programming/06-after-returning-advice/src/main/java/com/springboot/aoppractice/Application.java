package com.springboot.aoppractice;

import com.springboot.aoppractice.dao.AccountDAO;
import com.springboot.aoppractice.dao.AccountDAOImpl;
import com.springboot.aoppractice.dao.MembershipDAO;
import com.springboot.aoppractice.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
        return runner -> {
            demoTheAdvice(accountDAO, membershipDAO);
        };
    }

    private void demoTheAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        accountDAO.addAccount(new Account("gabe", "1"));
        System.out.println(accountDAO.findAccounts());
//        accountDAO.setName("yeah");
//        membershipDAO.addAccount(new Account("jackelyn", "1"));

    }

}
