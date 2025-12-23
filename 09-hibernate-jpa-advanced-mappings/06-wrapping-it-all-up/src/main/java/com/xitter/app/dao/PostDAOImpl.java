package com.xitter.app.dao;

import com.xitter.app.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO{
    private EntityManager entityManager;

    @Autowired
    public PostDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Post> findAllPosts() {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p ORDER BY p.id DESC", Post.class);
        List<Post> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<Post> findPostsByUsername(String username) {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post p WHERE p.user.username=:username ORDER BY p.id DESC", Post.class);
        query.setParameter("username", username);
        List<Post> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public Post findPostById(int id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    @Transactional
    public void createPost(Post post) {
        entityManager.persist(post);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    @Override
    @Transactional
    public void deletePostById(int id) {
        entityManager.remove(findPostById(id));
    }
}
