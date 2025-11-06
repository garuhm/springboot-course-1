package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = manager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = manager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        manager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = findById(id);
        manager.remove(student);
    }

    @Override
    @Transactional
    public int deleteWithFirstName(String firstName) {
        // query, not typed query, bc this doesn't need a type specified unlike the other ones
        Query query = manager.createQuery("DELETE FROM Student WHERE firstName=:firstName");
        query.setParameter("firstName", firstName);
        return query.executeUpdate();
    }
}
