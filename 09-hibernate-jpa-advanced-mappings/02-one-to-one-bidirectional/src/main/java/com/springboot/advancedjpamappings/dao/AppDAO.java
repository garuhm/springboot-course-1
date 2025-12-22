package com.springboot.advancedjpamappings.dao;

import com.springboot.advancedjpamappings.entity.Instructor;
import com.springboot.advancedjpamappings.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
