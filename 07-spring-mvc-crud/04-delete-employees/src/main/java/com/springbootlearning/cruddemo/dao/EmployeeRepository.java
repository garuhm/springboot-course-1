package com.springbootlearning.cruddemo.dao;

import com.springbootlearning.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findALlByOrderByLastNameAsc();
}
