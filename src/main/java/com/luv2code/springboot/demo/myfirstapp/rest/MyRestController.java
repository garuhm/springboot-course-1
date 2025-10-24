package com.luv2code.springboot.demo.myfirstapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    // expose "/" end point, returns "hello world!"
    // get request essentially

    @GetMapping("/")
    public String sayHello() {
        return "hello world!";
    }

    @GetMapping("/secret")
    public String secret() {
        return "you found da secret!!!";
    }
}
