package com.springbootlearning.springcoredemo.rest;

import com.springbootlearning.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach coach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("soccerCoach") Coach coach,
                          @Qualifier("soccerCoach") Coach anotherCoach) {
        this.coach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getWorkout(){
        return coach.getDailyWorkout();
    }

    @GetMapping("/check")
    public boolean check(){
        return coach == anotherCoach;
    }
}
