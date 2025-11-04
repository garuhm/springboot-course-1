package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Book;

public interface BookDAO {
    void save(Book book);
    Book findByid(Integer id);
}
