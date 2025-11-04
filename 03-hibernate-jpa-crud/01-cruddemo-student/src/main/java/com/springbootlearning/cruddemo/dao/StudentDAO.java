package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student student);
    Student findById(Integer id);
}
