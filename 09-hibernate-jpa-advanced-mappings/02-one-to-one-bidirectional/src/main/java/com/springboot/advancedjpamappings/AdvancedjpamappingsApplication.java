package com.springboot.advancedjpamappings;

import com.springboot.advancedjpamappings.dao.AppDAO;
import com.springboot.advancedjpamappings.entity.Instructor;
import com.springboot.advancedjpamappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedjpamappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedjpamappingsApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        return runner -> {
//            createInstructor(appDAO);
            deleteInstructorDetails(appDAO, 8);
        };
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
