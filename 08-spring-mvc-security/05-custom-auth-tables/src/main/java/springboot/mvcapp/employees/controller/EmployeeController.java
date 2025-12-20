package springboot.mvcapp.employees.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/leaders")
    public String leaders(){
        return "leaders";
    }

    @GetMapping("/systems")
    public String systems(){
        return "systems";
    }
}
