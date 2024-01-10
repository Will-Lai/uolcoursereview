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
public class CourseReviewController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/courseReview")
    public String insert(@RequestBody CourseReview courseReview) {
        String sql = "INSERT INTO courseReview(courseCode, rating, difficulty, workload, studyHourPerWeek, review) VALUE (:courseCode, :rating, :difficulty, :workload, :studyHourPerWeek, :review)";

        Map<String, Object> map = new HashMap<>();
        map.put("courseCode", courseReview.getCourseCode());
        map.put("rating", courseReview.getRating());
        map.put("difficulty", courseReview.getDifficulty());
        map.put("workload", courseReview.getWorkload());
        map.put("studyHourPerWeek", courseReview.getStudyHourPerWeek());
        map.put("review", courseReview.getReview());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int id = keyHolder.getKey().intValue();
        System.out.println("mysql generated Id: " + id);
        return "execute INSERT sql";
    }

    @GetMapping("/courseReview")
    public List<CourseReview> select() {
        String sql = "SELECT id, courseCode, rating, difficulty, workload, studyHourPerWeek, review FROM courseReview";
        Map<String, Object> map = new HashMap<>();

        List<CourseReview> list = namedParameterJdbcTemplate.query(sql, map, new CourseReviewRowMapper());
        return list;
    }

    @GetMapping("/courseReview/{courseCode}")
    public CourseReview select(@PathVariable String courseCode) {
        String sql = "SELECT id, courseCode, rating, difficulty, workload, studyHourPerWeek, review FROM courseReview WHERE courseCode = :courseCode";
        Map<String, Object> map = new HashMap<>();
        map.put("courseCode", courseCode);

        List<CourseReview> courseReview = namedParameterJdbcTemplate.query(sql, map, new CourseReviewRowMapper());

        return courseReview.get(0);
    }
}
