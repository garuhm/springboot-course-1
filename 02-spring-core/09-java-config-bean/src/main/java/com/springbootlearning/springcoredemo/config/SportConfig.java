package com.springbootlearning.springcoredemo.config;

import com.springbootlearning.springcoredemo.common.Coach;
import com.springbootlearning.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
