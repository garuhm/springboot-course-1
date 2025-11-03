package com.springbootlearning.springcoredemo.common;

public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000m as warmup";
    }
}
