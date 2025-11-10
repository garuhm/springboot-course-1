package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
    public Employee getEmployeeById(int id) {
        return manager.find(Employee.class, id);
    }

    @Override
    @Transactional
    public void createEmployee(Employee employee) {
        manager.persist(employee);
    }
}
