package com.example.uolcoursereview.service.impl;

import com.example.uolcoursereview.dao.StudentDao;
import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;
import com.example.uolcoursereview.service.StudentService;
import org.apache.commons.codec.cli.Digest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    private final static Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

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

        // check duplicate student email
        Student student = studentDao.getStudentByEmail(studentRequest.getEmail());
        if (student != null) {
            log.warn("duplicate student email: {}", student.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // use MD5 for password hashing
        String hashedPassword = DigestUtils.md5DigestAsHex(studentRequest.getPassword().getBytes());
        studentRequest.setPassword(hashedPassword);

        return studentDao.createStudent(studentRequest);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentDao.deleteStudentById(studentId);
    }
}
