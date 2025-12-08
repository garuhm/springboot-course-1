package com.springmvc.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }
    @PostMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @PostMapping("/processFormV2")
    public String letsShoutDude(HttpServletRequest req, Model model) {
        String name = req.getParameter("studentName");
        int num = Integer.parseInt(req.getParameter("number"));

        name = name.toUpperCase();
        String msg = "Yo! " + name;

        num++;
        model.addAttribute("message", msg);
        model.addAttribute("number", num);
        return "helloworld";
    }

    @PostMapping("/processFormV3")
    public String letsShoutDude(@RequestParam("studentName") String name, @RequestParam("number") String number, Model model) {
        int num = Integer.parseInt(number);

        name = name.toUpperCase();
        String msg = "Yo! " + name;

        num++;
        model.addAttribute("message", msg);
        model.addAttribute("number", num);
        return "helloworld";
    }
}

