package com.xitter.app.controller;

import com.xitter.app.model.WebPost;
import com.xitter.app.service.PostServiceImpl;
import com.xitter.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AppController {
    private UserService userService;
    private PostServiceImpl postService;

    @Autowired
    public AppController(UserService userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        System.out.println(principal.getName());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("timeline", postService.findAllPosts());
        model.addAttribute("createPost", new WebPost());
        return "home";
    }

    @PostMapping("/post")
    public String addPost(@Valid @ModelAttribute("createPost") WebPost webPost, BindingResult result, Principal principal){
        if(result.hasErrors()){
            return "home";
        }
        System.out.println(principal.getName());
        webPost.setUsername(principal.getName());
        postService.createPost(webPost);
        return "redirect:/home";
    }
}
