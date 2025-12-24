package com.xitter.app.service;

import com.xitter.app.entity.Comment;
import com.xitter.app.model.WebComment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsByUsername(String username);
    List<Comment> findCommentByPostId(int id);

    Comment findCommentById(int id);

    void createComment(WebComment comment);
    void updateComment(WebComment comment);
    void deleteCommentById(int id);
}
