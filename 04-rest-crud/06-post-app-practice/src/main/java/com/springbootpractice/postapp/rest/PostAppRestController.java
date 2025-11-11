package com.springbootpractice.postapp.rest;

import com.springbootpractice.postapp.entity.Comment;
import com.springbootpractice.postapp.entity.Post;
import com.springbootpractice.postapp.entity.User;
import com.springbootpractice.postapp.service.PostService;
import com.springbootpractice.postapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostAppRestController {
    private UserService userService;
    private PostService postService;
    private ObjectMapper mapper;

    @Autowired
    public PostAppRestController(UserService userService, PostService postService, ObjectMapper mapper) {
        this.userService = userService;
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        User result = userService.getUserById(id);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("User with id: " + id + " was not found");
        }
        return result;
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id){
        Post result = postService.getPostById(id);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + id + " was not found");
        }
        return result;
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getPostComments(@PathVariable int id){
        Post result = postService.getPostById(id);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + id + " was not found");
        }
        return postService.getCommentsByPost(id);
    }

    @GetMapping("/posts/{postId}/comments/{commentId")
    public Comment getPostComments(@PathVariable int postId, @PathVariable int commentId){
        Post result1 = postService.getPostById(postId);
        if(result1 == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }

        Comment result2 = postService.getCommentById(postId);
        if(result2 == null){
            // custom exception will be made later
            throw new RuntimeException("COmment with id: " + postId + " was not found");
        }
        return postService.getCommentById(commentId);
    }

    private <T> T apply(Map<String, Object> payload, T obj){
        ObjectNode originalNode = mapper.convertValue(obj,ObjectNode.class);
        ObjectNode payloadNode = mapper.convertValue(payload,ObjectNode.class);

        originalNode.setAll(payloadNode);
        return mapper.convertValue(originalNode, (Class<T>) obj.getClass());
    }

    private void deletePost(int postId){
        List<Integer> affectedUsers = postService.getUserIdsWithCommentOnPost(postId);
        for(Integer userId: affectedUsers){
            User user = userService.getUserById(userId);
            int amount = postService.getCommentAmountByUserInPost(userId, postId);

            int userAmount = user.getCommentCount();
            user.setCommentCount(userAmount - amount);

            userService.createUser(user);
        }
        postService.deleteCommentsByPost(postId);
        postService.deletePost(postId);
    }

    private void deleteCommentsByUserOnPost(int posterId, int postId){
        Post post = postService.getPostById(postId);
        int amount = postService.getCommentAmountByUserInPost(posterId, postId);

        int postAmount = post.getCommentCount();
        post.setCommentCount(postAmount - amount);

        postService.createPost(post);
    }
}
