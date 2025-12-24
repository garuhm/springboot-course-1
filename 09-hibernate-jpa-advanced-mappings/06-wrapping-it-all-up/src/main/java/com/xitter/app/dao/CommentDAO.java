package com.xitter.app.dao;

import com.xitter.app.entity.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> findCommentsByUsername(String username);
    List<Comment> findCommentByPostId(int id);

    Comment findCommentById(int id);

    void createComment(Comment comment);
    void updateComment(Comment comment);
    void deleteCommentById(int id);
}
