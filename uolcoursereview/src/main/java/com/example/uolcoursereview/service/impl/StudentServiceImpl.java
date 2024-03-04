package com.example.uolcoursereview.service.impl;

import com.example.uolcoursereview.dao.StudentDao;
import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;
import com.example.uolcoursereview.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public Integer createStudent(StudentRequest studentRequest) {
        return studentDao.createStudent(studentRequest);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentDao.deleteStudentById(studentId);
    }
}
