package com.springboot.advancedjpamappings.dao;

import com.springboot.advancedjpamappings.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id){
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);
        for(Course course : instructor.getCourses()){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    @Transactional
    public void save(Course course){
        entityManager.persist(course);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id){
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Instructor findCoursesByInstructorIdJoinFetch(int id){
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        Instructor i = query.getSingleResult();

        return i;
    }

    @Override
    public Course findCourseById(int id){
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course findCourseByIdJoinFetch(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);
        query.setParameter("data", id);

        Course i = query.getSingleResult();
        return i;
    }

    @Override
    @Transactional
    public void update(Instructor instructor){
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course){
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id){
        Course course = findCourseById(id);
        entityManager.remove(course);
    }

    @Transactional
    @Override
    public void deleteReviewById(int id){
        entityManager.remove(entityManager.find(Review.class, id));
    }

    @Override
    public Student findStudentById(int id){
        return entityManager.find(Student.class, id);
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id){
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id = :data", Course.class);
        query.setParameter("data", id);

        Course i = query.getSingleResult();
        return i;
    }

    @Override
    public Student findCourseAndStudentsByStudentId(int id){
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id = :data", Student.class);
        query.setParameter("data", id);

        Student i = query.getSingleResult();
        return i;
    }

    @Transactional
    @Override
    public void deleteStudent(int id){
        Student student = entityManager.find(Student.class, id);
        for(Course course : student.getCourses()){
            course.getStudents().remove(student);
        }
        entityManager.remove(student);
    }
}
