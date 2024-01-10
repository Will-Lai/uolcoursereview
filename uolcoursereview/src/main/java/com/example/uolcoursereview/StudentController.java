package com.example.uolcoursereview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/students")
    public String insert(@RequestBody Student student) {
        String sql = "INSERT INTO student(name, email) VALUE (:studentName, :studentEmail)";
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", student.getName());
        map.put("studentEmail", student.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql generated Id: " + id);
        return "execute INSERT sql";
    }

    @PostMapping("/students/batch")
    public String insertList(@RequestBody List<Student> studentList) {

        String sql = "INSERT INTO student(name, email) VALUE (:studentName, :studentEmail)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("studentName", student.getName());
            parameterSources[i].addValue("studentEmail", student.getEmail());
        }
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
        return "batch INSERT sql";
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable Integer studentId) {
        String sql = "DELETE FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        namedParameterJdbcTemplate.update(sql, map);

        return "execute delete sql";
    }

    @GetMapping("/students")
    public List<Student> select() {
        String sql = "SELECT id, name, email FROM student";
        Map<String, Object> map = new HashMap<>();

        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        return list;
    }

    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable Integer studentId ) {
        String sql = "SELECT id, name, email FROM student WHERE id = :studentId";
        Map<String, Object> map = new HashMap<>();

        map.put("studentId", studentId);
        List<Student> list = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        return list.get(0);
    }

}
