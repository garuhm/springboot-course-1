package com.xitter.app.controller;

import com.xitter.app.entity.User;
import com.xitter.app.model.WebPost;
import com.xitter.app.service.PostService;
import com.xitter.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AppController {
    private UserService userService;
    private PostService postService;

    @Autowired
    public AppController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        System.out.println(principal.getName());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("timeline", postService.findAllPosts());
        model.addAttribute("createPost", new WebPost());
        model.addAttribute("editPost", new WebPost());
        return "home";
    }

    @PostMapping("/post")
    public String addPost(@Valid @ModelAttribute("createPost") WebPost webPost, BindingResult result, Principal principal){
        if(result.hasErrors()){
            return "home";
        }
        webPost.setUsername(principal.getName());
        postService.createPost(webPost);
        int postID = postService.findPostsByUsername(principal.getName()).getFirst().getId();
        return "redirect:/post/" + postID;
    }

    @GetMapping("/post/{postId}")
    public String getPost(@PathVariable("postId") int postID, Model model, Principal principal){
        model.addAttribute("editPost", new WebPost());
        model.addAttribute("post", postService.findPostById(postID));
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "post";
    }

    @PutMapping("/post/{postId}")
    public String updatePost(@PathVariable("postId") int postID, @Valid @ModelAttribute("editPost") WebPost webPost, BindingResult result, Principal principal){
        if(result.hasErrors() || !postService.findPostById(postID).getUser().getUsername().equals(principal.getName())){
            return "home";
        }
        webPost.setId(postID);
        webPost.setUsername(principal.getName());
        postService.updatePost(webPost);
        return "redirect:/post/" + postID;
    }
}
