package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO{
    private EntityManager manager;

    @Autowired
    public StudentDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        manager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return manager.find(Student.class, id);
    }
}
