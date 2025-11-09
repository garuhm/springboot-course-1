package com.springbootlearning.restdemo.rest;

import com.springbootlearning.restdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student("Gabe", "Ruh"));
        students.add(new Student("Jackelyn", "Vuong-Dinh"));
        students.add(new Student("Nhi", "Thien Ma"));

        return students;
    }
}
