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

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post){
        post.setId(0);
        post.setCommentCount(0);
        post.setLikes(0);
        if(post.getContent().equals("")){
            // custom exception will be made later
            throw new RuntimeException("Missing fields in request. Please fill add content to the post");
        }

        return postService.createPost(post);
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

    @PatchMapping("/posts/{postId}")
    public Post updatePost(@PathVariable int postId, @RequestBody Map<String, Post> payload){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        if(payload.containsKey("id") || payload.containsKey("poster_id") || payload.containsKey("comment_count") || payload.containsKey("likes")) {
            // custom exception will be made later
            throw new RuntimeException("Request body cannot include anything but content. Please remove other keys from the request body");
        }

        return apply(payload,result);
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable int postId){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        deletePostComments(postId);
        postService.deletePost(postId);
        return "Post with id: " + postId + "was successfully deleted!";
    }

    @PostMapping("/posts/{postId}/likes")
    public Post likePost(@PathVariable int postId){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        result.addLike();
        return postService.createPost(result);
    }

    @DeleteMapping("/posts/{postId}/likes")
    public Post unlikePost(@PathVariable int postId){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        result.removeLike();
        return postService.createPost(result);
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

    @PostMapping("/posts/{postId}/comments")
    public Comment postComment(@PathVariable int postId, @RequestBody Comment comment){
        Post result = postService.getPostById(postId);
        if(result == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        comment.setId(0);
        comment.setPostId(postId);
        comment.setLikes(0);
        if(comment.getContent().equals("")){
            // custom exception will be made later
            throw new RuntimeException("Missing fields in request. Please fill add content to the post");
        }
        result.addComment();
        postService.createPost(result);
        return postService.createComment(comment);
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

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable int postId, @PathVariable int commentId, @RequestBody Map<String, Comment> payload){
        Post result1 = postService.getPostById(postId);
        if(result1 == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        Comment result2 = postService.getCommentById(commentId);
        if(result2 == null){
            // custom exception will be made later
            throw new RuntimeException("Comment with id: " + postId + " was not found");
        }
        if(payload.containsKey("id") || payload.containsKey("poster_id") || payload.containsKey("post_id") || payload.containsKey("likes")) {
            // custom exception will be made later
            throw new RuntimeException("Request body cannot include anything but content. Please remove other keys from the request body");
        }

        return apply(payload, result2);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable int postId, @PathVariable int commentId){
        Post result1 = postService.getPostById(postId);
        if(result1 == null){
            // custom exception will be made later
            throw new RuntimeException("Post with id: " + postId + " was not found");
        }
        Comment result2 = postService.getCommentById(commentId);
        if(result2 == null){
            // custom exception will be made later
            throw new RuntimeException("Comment with id: " + postId + " was not found");
        }
        result1.removeComment();
        postService.createPost(result1);
        postService.deleteComment(commentId);

    }

    private <T> T apply(Map<String, T> payload, T obj){
        ObjectNode originalNode = mapper.convertValue(obj,ObjectNode.class);
        ObjectNode payloadNode = mapper.convertValue(payload,ObjectNode.class);

        originalNode.setAll(payloadNode);
        return mapper.convertValue(originalNode, (Class<T>) obj.getClass());
    }

    private void deletePostComments(int postId){
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
