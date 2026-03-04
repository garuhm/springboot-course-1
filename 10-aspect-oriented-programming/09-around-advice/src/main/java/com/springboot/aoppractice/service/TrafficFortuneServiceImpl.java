package com.springboot.aoppractice.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        System.out.println("--calling fortune service--");
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(InterruptedException e){
            System.out.println(e);
        }

        return "it's your lucky day";
    }
}
