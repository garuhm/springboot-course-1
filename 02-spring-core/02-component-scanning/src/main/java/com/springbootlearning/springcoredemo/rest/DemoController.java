package com.springbootlearning.springcoredemo.rest;

import com.springbootlearning.springcoredemo.common.CricketCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private CricketCoach coach;

    @Autowired
    public DemoController(CricketCoach coach) {
        this.coach = coach;
    }

    @GetMapping("/dailyworkout")
    public String getWorkout(){
        return coach.getDailyWorkout();
    }
}
