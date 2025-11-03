package com.springbootlearning.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Dribble the ball!";
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("created bean: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("deleting bean: " + getClass().getSimpleName());
    }
}
