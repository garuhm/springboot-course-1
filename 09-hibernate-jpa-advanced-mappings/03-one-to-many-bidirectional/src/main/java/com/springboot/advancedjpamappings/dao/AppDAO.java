package com.springboot.advancedjpamappings.dao;

import com.springboot.advancedjpamappings.entity.Course;
import com.springboot.advancedjpamappings.entity.Instructor;
import com.springboot.advancedjpamappings.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);
}
