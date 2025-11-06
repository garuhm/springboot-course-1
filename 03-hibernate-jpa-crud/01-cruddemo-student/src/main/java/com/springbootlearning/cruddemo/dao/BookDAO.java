package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Book;

import java.util.List;

public interface BookDAO {
    void save(Book book);
    Book findByid(Integer id);
    List<Book> findAll();
    List<Book> findByTitle(String title);
    void update(Book book);
}
