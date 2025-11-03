package com.springbootlearning.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class SoccerCoach implements Coach{

    public SoccerCoach() {
        System.out.println("construct: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Dribble the ball!";
    }
}
