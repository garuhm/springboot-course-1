package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee getEmployeeById(int id);
    void createEmployee(Employee employee);
}
