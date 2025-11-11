package com.springbootpractice.postapp.dao;

import com.springbootpractice.postapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("FROM comment WHERE posterId=:id")
    List<Comment> findByUser(int id);

    @Query("FROM comment WHERE postId=:id")
    List<Comment> findByPost(int id);

    @Query("FROM comment WHERE posterId=:posterId AND postId=:postId")
    List<Comment> findNumberOfByUserInPost(int posterId, int postId);

    @Query("SELECT distinct postId FROM comment WHERE posterId=:id")
    List<Integer> findPostsWithCommentsByUser(int id);

    @Query("SELECT distinct posterId FROM comment WHERE postId=:id")
    List<Integer> findUsersWithCommentsOnPost(int id);

    @Query("DELETE FROM comment WHERE posterId=:id")
    int deleteByUser(int id);

    @Query("DELETE FROM comment WHERE postId=:id")
    int deleteByPost(int id);
}
