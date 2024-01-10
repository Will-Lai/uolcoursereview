package com.example.uolcoursereview;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        // get data from database
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");

        // transform data into Java object
        Student student = new Student(id, name, email);
        return student;
    }
}
