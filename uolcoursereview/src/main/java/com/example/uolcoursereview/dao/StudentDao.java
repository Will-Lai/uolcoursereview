package com.example.uolcoursereview.dao;

import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudents();

    Student getStudentById(Integer studentId);

    Integer createStudent(StudentRequest studentRequest);

    void deleteStudentById(Integer studentId);

    Student getStudentByEmail(String email);

}
