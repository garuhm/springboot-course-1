package com.springbootlearning.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SoccerCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Dribble the ball!";
    }
}
