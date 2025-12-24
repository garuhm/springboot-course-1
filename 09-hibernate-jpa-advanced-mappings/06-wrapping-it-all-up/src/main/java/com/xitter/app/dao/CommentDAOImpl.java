package com.xitter.app.dao;

import com.xitter.app.entity.Comment;
import com.xitter.app.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO{
    private EntityManager entityManager;

    @Autowired
    public CommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Comment> findCommentsByUsername(String username) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.user.username=:username ORDER BY c.id DESC", Comment.class);
        query.setParameter("username", username);
        List<Comment> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<Comment> findCommentByPostId(int id) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id=:id", Comment.class);
        query.setParameter("id", id);
        List<Comment> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public Comment findCommentById(int id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    @Transactional
    public void createComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(int id) {
        Comment comment = entityManager.find(Comment.class, id);
        comment.getPost().removeComment(comment);
        comment.getUser().removeComment(comment);
        entityManager.remove(comment);
    }
}
