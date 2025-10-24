package com.luv2code.springboot.demo.myfirstapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    // expose "/" end point, returns "hello world!"
    // get request essentially

    @Value("${test.name}")
    private String testPropName;

    @GetMapping("/")
    public String sayHello() {
        return testPropName;
    }

    @GetMapping("/secret")
    public String secret() {
        return "you found da secret!!!";
    }
}
