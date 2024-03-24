package com.example.uolcoursereview.dao.impl;

import com.example.uolcoursereview.dao.StudentDao;
import com.example.uolcoursereview.dto.StudentRequest;
import com.example.uolcoursereview.model.Student;
import com.example.uolcoursereview.rowmapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Student> getStudents() {
        String sql = "SELECT id, name, email FROM student";
        Map<String, Object> map = new HashMap<>();

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        return list;
    }

    @Override
    public Student getStudentById(Integer studentId) {

        String sql = "SELECT id, name, email " +
                "FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();

        map.put("studentId", studentId);
        List<Student> studentList = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        if (studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createStudent(StudentRequest studentRequest) {

        String sql = "INSERT INTO student(name, email, password) VALUE (:studentName, :studentEmail, :password)";
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", studentRequest.getName());
        map.put("studentEmail", studentRequest.getEmail());
        map.put("password", studentRequest.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int studentId = keyHolder.getKey().intValue();
        System.out.println("mysql generated Id: " + studentId);
        return studentId;
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Student getStudentByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = :email";
        Map<String, Object> map = new HashMap<>();

        map.put("email", email);
        List<Student> studentList = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());

        if (studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }
}
