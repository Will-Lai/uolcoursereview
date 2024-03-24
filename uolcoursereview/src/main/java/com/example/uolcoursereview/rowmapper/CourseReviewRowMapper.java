package com.example.uolcoursereview.rowmapper;

import com.example.uolcoursereview.model.CourseReview;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseReviewRowMapper implements RowMapper<CourseReview> {

    @Override
    public CourseReview mapRow(ResultSet rs, int rowNum) throws SQLException {
        // get data from database
        Integer id = rs.getInt("id");
        Integer studentId = rs.getInt("studentId");
        String courseCode = rs.getString("courseCode");
        Integer rating = rs.getInt("rating");
        Integer difficulty = rs.getInt("difficulty");
        Integer workload = rs.getInt("workload");
        Integer studyHourPerWeek = rs.getInt("studyHourPerWeek");
        String review = rs.getString("review");

        // transform data into Java object
        CourseReview courseReview = new CourseReview(id, studentId, courseCode, rating, difficulty, workload, studyHourPerWeek, review);
        return courseReview;
    }
}
