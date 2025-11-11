package com.springbootpractice.postapp.dao;

import com.springbootpractice.postapp.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("FROM comment WHERE posterId=:id")
    List<Comment> findByUser(int id);

    @Query("FROM comment WHERE postId=:id")
    List<Comment> findByPost(int id);

    @Transactional
    @Query("DELETE FROM comment WHERE posterId=:id")
    int deleteByUser(int id);

    @Transactional
    @Query("DELETE FROM comment WHERE postId=:id")
    int deleteByPost(int id);
}
