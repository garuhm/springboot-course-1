package com.xitter.app.service;

import com.xitter.app.dao.CommentDAO;
import com.xitter.app.dao.PostDAO;
import com.xitter.app.dao.UserDAO;
import com.xitter.app.entity.Comment;
import com.xitter.app.model.WebComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private PostDAO postDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(PostDAO postDAO, UserDAO userDAO, CommentDAO commentDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    public List<Comment> findCommentsByUsername(String username) {
        return commentDAO.findCommentsByUsername(username);
    }

    @Override
    public List<Comment> findCommentByPostId(int id) {
        return commentDAO.findCommentByPostId(id);
    }

    @Override
    public Comment findCommentById(int id) {
        return commentDAO.findCommentById(id);
    }

    @Override
    public void createComment(WebComment webComment) {
        Comment comment = new Comment(userDAO.findUserByName(webComment.getUsername()), postDAO.findPostById(webComment.getPostId()), webComment.getContent());
        postDAO.findPostById(webComment.getPostId()).addComment(comment);
        userDAO.findUserByName(webComment.getUsername()).addComment(comment);
        commentDAO.createComment(comment);
    }

    @Override
    public void updateComment(WebComment webComment) {
        Comment comment = commentDAO.findCommentById(webComment.getId());
        comment.setContent(webComment.getContent());
        commentDAO.updateComment(comment);
    }

    @Override
    public void deleteCommentById(int id) {
        commentDAO.deleteCommentById(id);
    }
}
