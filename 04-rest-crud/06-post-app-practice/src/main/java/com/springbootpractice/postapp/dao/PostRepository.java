package com.springbootpractice.postapp.dao;

import com.springbootpractice.postapp.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("FROM post WHERE posterId=:id")
    List<Post> getByUser(int id);

    @Query("DELETE FROM post WHERE posterId=:id")
    int deleteByUser(int id);
}
