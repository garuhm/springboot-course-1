package com.xitter.app.service;

import com.xitter.app.entity.Post;
import com.xitter.app.model.WebPost;

import java.util.List;

public interface PostService {
    List<Post> findAllPosts();
    List<Post> findPostsByUsername(String username);

    Post findPostById(int id);

    void createPost(WebPost post);
    void updatePost(WebPost post);
    void deletePostById(int id);
}
