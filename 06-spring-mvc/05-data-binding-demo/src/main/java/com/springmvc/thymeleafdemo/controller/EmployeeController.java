package com.springmvc.thymeleafdemo.controller;

import com.springmvc.thymeleafdemo.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Value("${countries}")
    private List<String> countries;
    @Value("${languages}")
    private List<String> languages;
    @Value("${programmingLanguages}")
    private List<String> programmingLanguages;

    @GetMapping("/showForm")
    public String showForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("programmingLanguages", programmingLanguages);
        return "show-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("employee") Employee employee){
        System.out.println(employee.getProgrammingLanguages());
        return "form-success";
    }
}
