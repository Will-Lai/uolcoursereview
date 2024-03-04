package com.example.uolcoursereview.service;

import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    Student getStudentById(Integer studentId);

    Integer createStudent(StudentRequest studentRequest);

    void deleteStudentById(Integer studentId);

}
