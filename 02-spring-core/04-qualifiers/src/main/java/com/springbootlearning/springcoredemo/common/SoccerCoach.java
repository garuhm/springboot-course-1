package com.springbootlearning.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Dribble the ball!";
    }
}
