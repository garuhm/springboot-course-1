package com.springboot.aoppractice;

import com.springboot.aoppractice.dao.AccountDAO;
import com.springboot.aoppractice.dao.AccountDAOImpl;
import com.springboot.aoppractice.dao.MembershipDAO;
import com.springboot.aoppractice.entity.Account;
import com.springboot.aoppractice.service.TrafficFortuneService;
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
    public CommandLineRunner commandLineRunner(TrafficFortuneService trafficFortuneService){
        return runner -> {
            demoTheAdvice(trafficFortuneService);
        };
    }

    private void demoTheAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println(trafficFortuneService.getFortune());
//        List<Account> accountList = null;
//
//        try{
//            boolean tripWire = false;
//            accountDAO.addAccount(new Account("gabe", "1"));
//            System.out.println("\n");
//            accountList = accountDAO.findAccounts(tripWire);
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println(accountList);
//        accountDAO.setName("yeah");
//        membershipDAO.addAccount(new Account("jackelyn", "1"));

    }

}
