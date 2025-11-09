package com.springbootlearning.restdemo.rest;

import com.springbootlearning.restdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return students.get(id);
    }
}
