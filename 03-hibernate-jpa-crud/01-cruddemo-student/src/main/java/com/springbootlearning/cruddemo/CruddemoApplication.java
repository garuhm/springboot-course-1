package com.springbootlearning.cruddemo;

import com.springbootlearning.cruddemo.dao.StudentDAO;
import com.springbootlearning.cruddemo.entity.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner runner(StudentDAO studentDAO){
        return runner -> {
//            createStudent(studentDAO);
//            createMultipleStudents(studentDAO);

//            createBook(bookDAO);
//            createMultipleBooks(bookDAO);
        };
    }

//    private void createBook(BookDAO bookDAO) {
//        Book book = new Book("Pride and Prejudice", "Jane Austen", 279);
//        System.out.println("Saving book...");
//
//        bookDAO.save(book);
//        System.out.println("Book saved!");
//        System.out.println("Book id: " + book.getId());
//    }
//
//    private void createMultipleBooks(BookDAO bookDAO) {
//        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 218);
//        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 281);
//        Book book3 = new Book("1984", "George Orwell", 328);
//        System.out.println("Saving book2...");
//
//        bookDAO.save(book1);
//        bookDAO.save(book2);
//        bookDAO.save(book3);
//        System.out.println("Books saved!");
//        System.out.println("Book 1 id: " + book1.getId());
//        System.out.println("Book 2 id: " + book2.getId());
//        System.out.println("Book 3 id: " + book3.getId());
//    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        Student student1 = new Student("Jackelyn", "Vuong", "jvoung@gmail.com");
        Student student2 = new Student("Nhi", "Ma", "nthienma@gmail.com");
        Student student3 = new Student("Emily", "Tinder", "etinder@gmail.com");
        Student student4 = new Student("Dani", "LaRue", "dlarue@gmail.com");

        System.out.println("Saving students....");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
        studentDAO.save(student4);
        System.out.println("Students saved!");
        System.out.println("Student 1 id: " + student1.getId());
        System.out.println("Student 2 id: " + student2.getId());
        System.out.println("Student 3 id: " + student3.getId());
        System.out.println("Student 4 id: " + student4.getId());
    }

    private void createStudent(StudentDAO studentDAO){
        Student student = new Student("Gabe", "Ruh", "garuhmm@gmail.com");
        System.out.println("Saving student....");
        studentDAO.save(student);

        System.out.println("Student saved!");
        System.out.println("Student id: " + student.getId());
    }
}
