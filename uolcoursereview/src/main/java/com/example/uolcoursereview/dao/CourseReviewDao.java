package com.example.uolcoursereview.dao;

import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.model.CourseReview;

import java.util.List;

public interface CourseReviewDao {

    CourseReview getCourseReviewById(Integer courseReviewId);
    List<CourseReview> getCourseReviews(CourseReviewQueryParams courseReviewQueryParams);
    Integer createCourseReview(CourseReviewRequest courseReviewRequest);

}
