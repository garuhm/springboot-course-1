package com.springbootlearning.cruddemo.service;

import com.springbootlearning.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
//    Employee findById(int id);
//    void createEmployee(Employee employee);
}
