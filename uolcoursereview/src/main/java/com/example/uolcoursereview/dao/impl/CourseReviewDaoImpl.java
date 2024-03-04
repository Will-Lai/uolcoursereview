package com.example.uolcoursereview.dao.impl;

import com.example.uolcoursereview.dao.CourseReviewDao;
import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.model.CourseReview;
import com.example.uolcoursereview.rowmapper.CourseReviewRowMapper;
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
public class CourseReviewDaoImpl implements CourseReviewDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public CourseReview getCourseReviewById(Integer courseReviewId) {
        String sql = "SELECT id, courseCode, rating, difficulty, workload, studyHourPerWeek, review " +
                "FROM courseReview WHERE id = :courseReviewId ";
        Map<String, Object> map = new HashMap<>();

        map.put("courseReviewId", courseReviewId);

        List<CourseReview> courseReviewList = namedParameterJdbcTemplate.query(sql, map, new CourseReviewRowMapper());

        if (courseReviewList.size() > 0) {
            return courseReviewList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CourseReview> getCourseReviews(CourseReviewQueryParams courseReviewQueryParams) {

        String sql = "SELECT id, courseCode, rating, difficulty, workload, studyHourPerWeek, review " +
                "FROM courseReview WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();
        sql = this.addFilteringSql(sql, map, courseReviewQueryParams);

        List<CourseReview> list = namedParameterJdbcTemplate.query(sql, map, new CourseReviewRowMapper());
        return list;
    }

    @Override
    public Integer createCourseReview(CourseReviewRequest courseReviewRequest) {

        String sql = "INSERT INTO courseReview(courseCode, rating, difficulty, workload, studyHourPerWeek, review) " +
                "VALUE (:courseCode, :rating, :difficulty, :workload, :studyHourPerWeek, :review)";

        Map<String, Object> map = new HashMap<>();
        map.put("courseCode", courseReviewRequest.getCourseCode());
        map.put("rating", courseReviewRequest.getRating());
        map.put("difficulty", courseReviewRequest.getDifficulty());
        map.put("workload", courseReviewRequest.getWorkload());
        map.put("studyHourPerWeek", courseReviewRequest.getStudyHourPerWeek());
        map.put("review", courseReviewRequest.getReview());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int courseReviewId = keyHolder.getKey().intValue();
        return courseReviewId;
    }

    private String addFilteringSql(String sql, Map<String, Object> map, CourseReviewQueryParams courseReviewQueryParams) {
        if (courseReviewQueryParams.getCourseCode() != null) {
            sql = sql + " AND courseCode = :courseCode";
            map.put("courseCode",  courseReviewQueryParams.getCourseCode());
        }

        return sql;
    }

}
