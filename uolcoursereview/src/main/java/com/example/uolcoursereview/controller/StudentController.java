package com.example.uolcoursereview.controller;

import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;
import com.example.uolcoursereview.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> studentList = studentService.getStudents();

        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId ) {
        Student student = studentService.getStudentById(studentId);

        if (student != null) {
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        Integer studentId = studentService.createStudent(studentRequest);

        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {

        studentService.deleteStudentById(studentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
