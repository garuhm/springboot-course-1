package com.springmvc.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @RequestMapping("/processFormV2")
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
}

