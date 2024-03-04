package com.example.uolcoursereview.service.impl;

import com.example.uolcoursereview.dao.CourseReviewDao;
import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.model.CourseReview;
import com.example.uolcoursereview.service.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseReviewServiceImpl implements CourseReviewService {

    @Autowired
    private CourseReviewDao courseReviewDao;

    @Override
    public CourseReview getCourseReviewById(Integer courseReviewId) {
        return courseReviewDao.getCourseReviewById(courseReviewId);
    }

    @Override
    public List<CourseReview> getCourseReviews(CourseReviewQueryParams courseReviewQueryParams) {
        return courseReviewDao.getCourseReviews(courseReviewQueryParams);
    }

    @Override
    public Integer createCourseReview(CourseReviewRequest courseReviewRequest) {
        return courseReviewDao.createCourseReview(courseReviewRequest);
    }
}
