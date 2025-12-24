package com.xitter.app.dao;

import com.xitter.app.entity.Post;
import com.xitter.app.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUserByName(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username=:username", User.class);
        query.setParameter("username", username);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> findByQuery(String queryTerm){
        TypedQuery<User> query = entityManager.createQuery("from User where username LIKE :query OR displayName LIKE :query", User.class);
        query.setParameter("query", "%" + queryTerm + "%");
        List<User> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    @Transactional
    public void save(User user){
        entityManager.merge(user);
    }
}
