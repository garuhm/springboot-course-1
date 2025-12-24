package com.xitter.app.controller;

import com.xitter.app.entity.Post;
import com.xitter.app.entity.User;
import com.xitter.app.model.WebComment;
import com.xitter.app.model.WebPost;
import com.xitter.app.service.CommentService;
import com.xitter.app.service.PostService;
import com.xitter.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {
    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    @Autowired
    public AppController(UserService userService, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
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
        model.addAttribute("comments", commentService.findCommentByPostId(postID));
        model.addAttribute("createComment", new WebComment());
        model.addAttribute("editComment", new WebComment());
        model.addAttribute("post", postService.findPostById(postID));
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "post";
    }

    @PutMapping("/post/{postId}")
    public String updatePost(@PathVariable("postId") int postID, @Valid @ModelAttribute("editPost") WebPost webPost, BindingResult result, Principal principal){
        if(result.hasErrors() || !postService.findPostById(postID).getUser().getUsername().equals(principal.getName())){
            return "redirect:/post/" + postID;
        }
        webPost.setId(postID);
        webPost.setUsername(principal.getName());
        postService.updatePost(webPost);
        return "redirect:/post/" + postID;
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable("postId") int postID, Principal principal){
        if(!postService.findPostById(postID).getUser().getUsername().equals(principal.getName())){
            return "redirect:/post/" + postID;
        }
        postService.deletePostById(postID);
        return "redirect:/home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query")String query, Model model, Principal principal){
        model.addAttribute("posts", postService.findByQuery(query));
        model.addAttribute("users", userService.findByQuery(query));

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("createPost", new WebPost());
        return "search";
    }

    @PostMapping("/post/{postId}/comment")
    public String addComment(@PathVariable("postId") int postID, @Valid @ModelAttribute("createComment") WebComment webComment, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "home";
        }
        webComment.setUsername(principal.getName());
        webComment.setPostId(postID);
        commentService.createComment(webComment);
        return "redirect:/post/" + postID;
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public String updateComment(@PathVariable("postId") int postID, @PathVariable("commentId") int commentId, @Valid @ModelAttribute("editPost") WebComment webComment, BindingResult result, Principal principal){
        if(result.hasErrors() || !commentService.findCommentById(commentId).getUser().getUsername().equals(principal.getName())){
            return "redirect:/post/" + postID;
        }
        webComment.setId(commentId);
        webComment.setUsername(principal.getName());
        commentService.updateComment(webComment);
        return "redirect:/post/" + postID;
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String deletePost(@PathVariable("postId") int postID, @PathVariable("commentId") int commentId, Principal principal){
        if(!commentService.findCommentById(commentId).getUser().getUsername().equals(principal.getName())){
            return "redirect:/post/" + postID;
        }
        commentService.deleteCommentById(commentId);
        return "redirect:/post/" + postID;
    }
}
