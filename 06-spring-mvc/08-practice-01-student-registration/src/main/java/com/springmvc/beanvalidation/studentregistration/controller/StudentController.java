package com.springmvc.beanvalidation.studentregistration.controller;

import com.springmvc.beanvalidation.studentregistration.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, editor);
    }

    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/confirmation")
    public String confirmForm(@Valid @ModelAttribute("student") Student student, BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        return "confirmation";
    }
}
