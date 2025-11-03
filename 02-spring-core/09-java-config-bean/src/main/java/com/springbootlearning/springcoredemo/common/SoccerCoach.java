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
}
