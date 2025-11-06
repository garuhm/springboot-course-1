package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Post;

import java.util.List;

public interface PostDAO {
    void save(Post post);
    Post findById(int id);
    List<Post> findAll();
    List<Post> findByContent(String content);
    Post updateById(int id, String content);
    Post deleteById(int id);
    int deleteByContent(String content);
}
