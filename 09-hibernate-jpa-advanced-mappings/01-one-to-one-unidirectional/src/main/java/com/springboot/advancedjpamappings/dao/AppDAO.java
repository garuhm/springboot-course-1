package com.springboot.advancedjpamappings.dao;

import com.springboot.advancedjpamappings.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
}
