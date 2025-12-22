package com.springboot.advancedjpamappings;

import com.springboot.advancedjpamappings.dao.AppDAO;
import com.springboot.advancedjpamappings.entity.Course;
import com.springboot.advancedjpamappings.entity.Instructor;
import com.springboot.advancedjpamappings.entity.InstructorDetail;
import com.springboot.advancedjpamappings.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedjpamappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedjpamappingsApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner -> {
//            createInstructorWithCoursesReviews(appDAO);

            deleteCourse(appDAO, 10);
        };
    }

    private void findCoursesWithReviews(AppDAO appDAO, int id){
        Course course = appDAO.findCourseByIdJoinFetch(id);
        System.out.println(course);
    }

    private void createInstructorWithCoursesReviews(AppDAO appDAO){
        Instructor instructor = new Instructor("Gabe", "Ruh", "garuhm@gmail.com");
        instructor.setInstructorDetail(new InstructorDetail("http:///www.youtube.com/gaberuh", "guitar"));

        Course course = new Course("Spring Boot 101");
        Course course2 = new Course("Spring Boot Zero to Hero");
        instructor.add(course);
        instructor.add(course2);

        course.add(new Review("good"));
        course.add(new Review("amazing"));
        course2.add(new Review("saved my life!"));

        System.out.println(instructor);

        appDAO.save(instructor);
    }

    public void deleteCourse(AppDAO appDAO, int id){
        appDAO.deleteCourseById(id);
        System.out.println("done!");
    }

    public void updateCourse(AppDAO appDAO, int id){
        Course course = appDAO.findCourseById(id);
        course.setTitle("Spring Boot 101 full!");
        System.out.println(course.getTitle());
        appDAO.update(course);
    }

    public void updateInstructor(AppDAO appDAO, int id){
        Instructor instructor = appDAO.findInstructorById(id);
        instructor.setLastName("Ruh-Mosquera");
        System.out.println(instructor.getLastName());
        appDAO.update(instructor);
    }

    private void findInstructorWithCourses(AppDAO appDAO, int id){
        Instructor instructor = appDAO.findCoursesByInstructorIdJoinFetch(id);
        System.out.println(instructor);
    }

    private void createInstructorWithCourses(AppDAO appDAO){
        Instructor instructor = new Instructor("Gabe", "Ruh", "garuhm@gmail.com");
        instructor.setInstructorDetail(new InstructorDetail("http:///www.youtube.com/gaberuh", "guitar"));

        Course course = new Course("Spring Boot 101");
        Course course2 = new Course("Spring Boot Zero to Hero");
        instructor.add(course);
        instructor.add(course2);

        System.out.println(instructor);

        appDAO.save(instructor);
    }

    private void deleteInstructorDetails(AppDAO appDAO, int id){
        appDAO.deleteInstructorDetailById(id);
        System.out.println("done!");
    }

    private void findInstructorDetails(AppDAO appDAO, int id){
        Instructor instructor = appDAO.findInstructorDetailById(id).getInstructor();

        System.out.println(instructor);
    }

    private void deleteInstructor(AppDAO appDAO, int id){
        appDAO.deleteInstructorById(id);
        System.out.println("done!");
    }

    private void findInstructor(AppDAO appDAO, int id){
        Instructor instructor = appDAO.findInstructorById(id);

        System.out.println(instructor);
    }

    private void createInstructor(AppDAO appDAO){
        Instructor instructor = new Instructor("Gabe", "Ruh", "garuhm@gmail.com");
        instructor.setInstructorDetail(new InstructorDetail("http:///www.youtube.com/gaberuh", "guitar"));

        System.out.println(instructor);

        appDAO.save(instructor);
    }
}
