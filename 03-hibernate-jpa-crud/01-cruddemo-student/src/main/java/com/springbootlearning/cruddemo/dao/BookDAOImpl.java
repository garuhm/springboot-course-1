package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Book findByid(Integer id) {
        return manager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = manager.createQuery("FROM Book", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = manager.createQuery("FROM Book WHERE title=:title", Book.class);
        query.setParameter("title", title);

        return query.getResultList();
    }
}
