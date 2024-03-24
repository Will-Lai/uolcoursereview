package com.example.uolcoursereview.service.impl;

import com.example.uolcoursereview.dao.CourseReviewDao;
import com.example.uolcoursereview.dao.StudentDao;
import com.example.uolcoursereview.dto.CourseReviewES;
import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.model.CourseReview;
import com.example.uolcoursereview.model.Student;
import com.example.uolcoursereview.service.CourseReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CourseReviewServiceImpl implements CourseReviewService {

    @Autowired
    private CourseReviewDao courseReviewDao;
    @Autowired
    private StudentDao studentDao;

    private final static Logger log = LoggerFactory.getLogger(CourseReviewServiceImpl.class);


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

        Student student = studentDao.getStudentById(courseReviewRequest.getStudentId());

        if (student == null) {
            log.warn("studentId {} does not exist", courseReviewRequest.getStudentId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Integer courseReviewId = courseReviewDao.createCourseReview(courseReviewRequest);

        return courseReviewId;
    }

    @Override
    public void updateCourseReview(Integer courseReviewId, CourseReviewRequest courseReviewRequest) {
        courseReviewDao.updateCourseReview(courseReviewId, courseReviewRequest);
    }

    @Override
    public CourseReviewES convertToEsObject(CourseReview courseReview) {

        CourseReviewES courseReviewES = new CourseReviewES(
                courseReview.getId(),
                courseReview.getStudentId(),
                courseReview.getCourseCode(),
                courseReview.getRating(),
                courseReview.getDifficulty(),
                courseReview.getWorkload(),
                courseReview.getStudyHourPerWeek(),
                courseReview.getReview()
        );

        return courseReviewES;
    }
}
