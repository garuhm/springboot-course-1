package com.springbootlearning.restdemo.rest;

import com.springbootlearning.restdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;

    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Gabe", "Ruh"));
        students.add(new Student("Jackelyn", "Vuong-Dinh"));
        students.add(new Student("Nhi", "Thien Ma"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id){
        if(id > students.size() - 1 || id < 0){
            throw new StudentNotFoundException("Student of id " + id + " not found");
        }

        return students.get(id);
    }
}
