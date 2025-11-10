package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    private EntityManager manager;

    @Autowired
    public EmployeeDAOImpl(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = manager.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findByID(int id) {
        return manager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        // save OR update, depending if exists
        return manager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        manager.remove(manager.find(Employee.class, id));
    }
}
