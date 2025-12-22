package com.xitter.app.controller;

import com.xitter.app.entity.User;
import com.xitter.app.model.WebUser;
import com.xitter.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService service;

    @Autowired
    public RegisterController(UserService service) {
        this.service = service;
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/")
    public String showRegister(Model model){
        model.addAttribute("webUser", new WebUser());
        return "register/register";
    }

    @PostMapping("/process")
    public String register(@Valid @ModelAttribute("webUser") WebUser webUser, BindingResult result, Model model, RedirectAttributes redirectAttributes ){
        if(result.hasErrors()){
            return "register/register";
        }
        User existing = service.findByUsername(webUser.getUsername());
        if(existing != null){
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "Username already exists");
            return "register/register";
        }
        if(webUser.getDisplayName() == null){
            webUser.setDisplayName(webUser.getDisplayName());
        }
        service.save(webUser);
        redirectAttributes.addFlashAttribute("registered", true);
        return "redirect:/login";
    }
}
