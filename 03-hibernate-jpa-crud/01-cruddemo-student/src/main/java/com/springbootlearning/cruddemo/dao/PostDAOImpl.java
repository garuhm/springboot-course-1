package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements  PostDAO{
    private EntityManager manager;

    @Autowired
    public PostDAOImpl(EntityManager manager){
        this.manager = manager;
    }

    @Override
    @Transactional
    public void save(Post post) {
        manager.persist(post);
    }

    @Override
    public Post findById(int id) {
        return manager.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        String queryString = "FROM Post";
        TypedQuery<Post> query = manager.createQuery(queryString, Post.class);
        return query.getResultList();
    }

    @Override
    public List<Post> findByContent(String content) {
        String queryString = "FROM Post WHERE content LIKE '%" + content + "%'";
        TypedQuery<Post> query = manager.createQuery(queryString, Post.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Post updateById(int id, String content) {
        Post post = manager.find(Post.class, id);
        post.setContent(content);
        post.setEdited(true);
        manager.merge(post);
        return post;
    }

    @Override
    @Transactional
    public Post deleteById(int id) {
        Post post = manager.find(Post.class, id);
        manager.remove(post);
        return post;
    }

    @Override
    @Transactional
    public int deleteByContent(String content) {
        String queryText = "DELETE FROM Post WHERE content LIKE '%" + content + "%'";
        Query query = manager.createQuery(queryText);
        return query.executeUpdate();
    }
}
