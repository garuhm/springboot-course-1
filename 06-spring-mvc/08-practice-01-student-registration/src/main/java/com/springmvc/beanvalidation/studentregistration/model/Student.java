package com.springmvc.beanvalidation.studentregistration.model;

import com.springmvc.beanvalidation.studentregistration.validation.StartsWith;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Student {
    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    private String lastName;

    @NotNull(message = "required")
    @Min(value = 1, message = "invalid id, must be 1 or greater")
    private Integer id;

    @StartsWith(value = "CSI", message = "must start with CSI")
    private String courseCode;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
