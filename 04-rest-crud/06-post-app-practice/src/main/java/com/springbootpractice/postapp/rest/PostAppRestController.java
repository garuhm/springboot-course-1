package com.springbootpractice.postapp.rest;

import com.springbootpractice.postapp.entity.Comment;
import com.springbootpractice.postapp.entity.Post;
import com.springbootpractice.postapp.entity.User;
import com.springbootpractice.postapp.service.PostService;
import com.springbootpractice.postapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        user.setId(0);
        user.setCommentCount(0);
        if(user.getEmail().equals("") || user.getUsername().equals("")){
            // custom exception will be made later
            throw new RuntimeException("Missing fields in request. Please fill in the username and email");
        }
        return userService.createUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        if(user.getEmail().equals("") || user.getUsername().equals("") || user.getId() == 0 ){
            // custom exception will be made later
            throw new RuntimeException("Missing fields in request. Please fill in the id, username, and email");
        }
        return userService.createUser(user);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId){
        User result = userService.getUserById(userId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("User with id: " + userId + " was not found");
        }
        return result;
    }

    @PatchMapping("/user/{userId}")
    public User patchUser(@PathVariable int userId, @RequestBody Map<String, User> payload){
        User result = userService.getUserById(userId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("User with id: " + userId + " was not found");
        } if(payload.containsKey("id")){
            // custom exception will be made later
            throw new RuntimeException("Request body cannot include id. Please remove id from the request body");
        }

        return apply(payload,result);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId){
        User result = userService.getUserById(userId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("User with id: " + userId + " was not found");
        }

        List<Integer> postsCommentedOnByUser = postService.getPostIdsWithCommentsByUser(userId);
        for(Integer postId : postsCommentedOnByUser) {
            deleteCommentsByUserOnPost(userId, postId);
        }
        List<Post> postsMadeByUser = postService.getPostsByUser(userId);
        for(Post post : postsMadeByUser) {
            deletePost(post.getId());
        }
        postService.deletePostsByUser(userId);
        userService.deleteUser(userId);
        return "User with id: " + userId + "was successfully deleted!";
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable int postId){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        return result;
    }

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getPostComments(@PathVariable int postId){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        return postService.getCommentsByPost(postId);
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

    private <T> T apply(Map<String, T> payload, T obj){
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
    }

    private void deleteCommentsByUserOnPost(int posterId, int postId){
        Post post = postService.getPostById(postId);
        int amount = postService.getCommentAmountByUserInPost(posterId, postId);

        int postAmount = post.getCommentCount();
        post.setCommentCount(postAmount - amount);

        postService.createPost(post);
    }
}
