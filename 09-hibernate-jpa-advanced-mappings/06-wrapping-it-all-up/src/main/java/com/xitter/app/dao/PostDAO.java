package com.xitter.app.dao;

import com.xitter.app.entity.Post;

import java.util.List;

public interface PostDAO {
    List<Post> findAllPosts();
    List<Post> findPostsByUsername(String username);

    Post findPostById(int id);

    void createPost(Post post);
    void updatePost(Post post);
    void deletePostById(int id);
}
