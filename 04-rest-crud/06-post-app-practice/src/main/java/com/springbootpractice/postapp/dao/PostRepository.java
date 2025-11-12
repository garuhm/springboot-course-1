package com.springbootpractice.postapp.dao;

import com.springbootpractice.postapp.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("FROM Post WHERE posterId=:id")
    List<Post> getByUser(int id);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.posterId=:id")
    int deleteByUser(int id);
}
