package com.springboot.advancedjpamappings.dao;

import com.springboot.advancedjpamappings.entity.Course;
import com.springboot.advancedjpamappings.entity.Instructor;
import com.springboot.advancedjpamappings.entity.InstructorDetail;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);
    Instructor findCoursesByInstructorIdJoinFetch(int id);

    void save(Course course);
    Course findCourseById(int id);
    Course findCourseByIdJoinFetch(int id);

    void update(Instructor instructor);
    void update(Course course);

    void deleteCourseById(int id);

    @Transactional
    void deleteReviewById(int id);
}
