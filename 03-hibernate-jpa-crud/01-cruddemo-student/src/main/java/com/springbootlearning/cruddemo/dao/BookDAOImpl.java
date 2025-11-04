package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO{
    private EntityManager manager;

    @Autowired
    public BookDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional
    public void save(Book book) {
        manager.persist(book);
    }
}
