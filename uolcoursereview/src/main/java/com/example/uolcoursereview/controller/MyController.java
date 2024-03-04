package com.example.uolcoursereview.controller;

import com.example.uolcoursereview.model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/test")
    public String test() {
        System.out.println("Hi!");
        return "Hello world!";
    }

    @RequestMapping("/user")
    public Student user() {
        // test
        Student student = new Student();
        student.setName("Judy");
        return student;
    }
}
