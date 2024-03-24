package com.example.uolcoursereview.service;

import com.example.uolcoursereview.dto.CourseReviewES;
import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.model.CourseReview;

import java.util.List;

public interface CourseReviewService {

    CourseReview getCourseReviewById(Integer courseReviewId);
    List<CourseReview> getCourseReviews(CourseReviewQueryParams courseReviewQueryParams);

    Integer createCourseReview(CourseReviewRequest courseReviewRequest);

    void updateCourseReview(Integer courseReviewId, CourseReviewRequest courseReviewRequest);

    CourseReviewES convertToEsObject(CourseReview courseReview);
}
