package com.springbootlearning.cruddemo;

import com.springbootlearning.cruddemo.dao.BookDAO;
import com.springbootlearning.cruddemo.dao.PostDAO;
import com.springbootlearning.cruddemo.dao.StudentDAO;
import com.springbootlearning.cruddemo.entity.Book;
import com.springbootlearning.cruddemo.entity.Post;
import com.springbootlearning.cruddemo.entity.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner runner(PostDAO postDAO) {
        return runner -> {
//            String content = "Not deleted.....";
//            savePost(postDAO, content);

//            System.out.println(postDAO.findById(1));
//            List<Post> initPosts = postDAO.findAll();
//            for(Post post: initPosts){
//                System.out.println(post);
//            }
//            List<Post> withContent = postDAO.findByContent("Post");
//            for(Post post: withContent){
//                System.out.println(post);
//            }

//            postDAO.updateById(3, "Yes really not deleted... for now");
//            System.out.println(postDAO.findById(3));

//            postDAO.deleteById(3);
            postDAO.deleteByContent("Post");
            List<Post> initPosts = postDAO.findAll();
            for(Post post: initPosts){
                System.out.println(post);
            }
        };
    }

    private void savePost(PostDAO postDAO, String content) {
        postDAO.save(new Post(content));
        System.out.println("saved!");
    }


    //    @Bean
//    public CommandLineRunner runner(StudentDAO studentDAO, BookDAO bookDAO){
//        return runner -> {
//            createStudent(studentDAO);
//            createMultipleStudents(studentDAO);
//
//            createBook(bookDAO);
//            createMultipleBooks(bookDAO);
//
//            readStudent(studentDAO);
//            readBook(bookDAO);
//
//            findAllStudents(studentDAO);
//            findStudentByLastName(studentDAO);
//            findAllBooks(bookDAO);
//            findBookByTitle(bookDAO);
//
//            updateStudent(studentDAO);
//            updateBook(bookDAO);
//
//            deleteStudent(studentDAO);
//            deleteStudentsByFirstName(studentDAO);
//        };
//    }
    // DELETE
    private void deleteStudentsByFirstName(StudentDAO studentDAO){
        System.out.println("deleting all with first name Fake");
        int rows = studentDAO.deleteWithFirstName("Fake");
        System.out.println("deleted " + rows + " rows!");
    }

    private void deleteStudent(StudentDAO studentDAO){
        System.out.println("deleting student with id 7....");
        studentDAO.delete(7);
        System.out.println("deleted!");
    }

    // UPDATE
    private void updateBook(BookDAO bookDAO){
        System.out.println("finding...");
        Book book = bookDAO.findByid(2);
        System.out.println("setting value...");
        book.setPages(219);
        System.out.println("updating....");
        bookDAO.update(book);
        System.out.println("updated in db!");
        System.out.println(book.toString());
    }

    private void updateStudent(StudentDAO studentDAO){
        System.out.println("finding...");
        Student student = studentDAO.findById(3);
        System.out.println("setting value...");
        student.setLastName("Thien Ma");
        System.out.println("updating....");
        studentDAO.update(student);
        System.out.println("updated in db!");
        System.out.println(student.toString());
    }

    //READ
    private void findBookByTitle(BookDAO bookDAO){
        List<Book> books = bookDAO.findByTitle("1984");
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

    private void findAllBooks(BookDAO bookDAO){
        List<Book> books = bookDAO.findAll();
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

    private void findStudentByLastName(StudentDAO studentDAO){
        List<Student> students = studentDAO.findByLastName("Ruh");
        for(Student student : students){
            System.out.println(student.toString());
        }
    }

    private void findAllStudents(StudentDAO studentDAO){
        List<Student> students = studentDAO.findAll();
        for(Student student : students){
            System.out.println(student.toString());
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("creating...");
        Student student = new Student("Miyako", "Glemser", "mglemser@gmail.com");

        System.out.println("saving..");
        studentDAO.save(student);

        System.out.println("Student saved!");
        System.out.println("Student id: " + student.getId());

        System.out.println("retrieving w/id....");
        Student retrieved = studentDAO.findById(student.getId());

        System.out.println("student: ");
        System.out.println(retrieved.toString());
    }

    private void readBook(BookDAO bookDAO) {
        System.out.println("creating....");
        Book book = new Book("The Midnight Library", "Matt Haig", 304);

        System.out.println("saving...");
        bookDAO.save(book);
        System.out.println("Book saved!");
        System.out.println("Book id: " + book.getId());

        System.out.println("finding by id...");
        Book retrieved = bookDAO.findByid(book.getId());

        System.out.println("book: ");
        System.out.println(retrieved.toString());
    }

    //CREATE
    private void createBook(BookDAO bookDAO) {
        Book book = new Book("My book", "Me", 200);
        System.out.println("Saving book...");

        bookDAO.save(book);
        System.out.println("Book saved!");
        System.out.println("Book id: " + book.getId());
    }

    private void createMultipleBooks(BookDAO bookDAO) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 218);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 281);
        Book book3 = new Book("1984", "George Orwell", 328);
        System.out.println("Saving book2...");

        bookDAO.save(book1);
        bookDAO.save(book2);
        bookDAO.save(book3);
        System.out.println("Books saved!");
        System.out.println("Book 1 id: " + book1.getId());
        System.out.println("Book 2 id: " + book2.getId());
        System.out.println("Book 3 id: " + book3.getId());
    }

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
        Student student = new Student("Fake", "Ruh", "garuhmm@gmail.com");
        System.out.println("Saving student....");
        studentDAO.save(student);

        System.out.println("Student saved!");
        System.out.println("Student id: " + student.getId());
    }
}
