package com.springbootpractice.postapp.rest;

import com.springbootpractice.postapp.entity.Comment;
import com.springbootpractice.postapp.entity.Post;
import com.springbootpractice.postapp.entity.User;
import com.springbootpractice.postapp.service.PostService;
import com.springbootpractice.postapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
