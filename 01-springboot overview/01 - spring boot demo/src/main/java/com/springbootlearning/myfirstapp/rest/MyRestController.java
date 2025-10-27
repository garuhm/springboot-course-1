package com.springbootlearning.myfirstapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    @Value("${test.val}")
    private String testProp;

    @GetMapping("/")
    public String sayHello() {
        return "Hello world! " + testProp;
    }

    @GetMapping("/secret")
    public String secret() {
        return "you found the secret!";
    }
}
