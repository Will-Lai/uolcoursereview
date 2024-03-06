package com.example.uolcoursereview.controller;

import com.example.uolcoursereview.dto.CourseReviewQueryParams;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import com.example.uolcoursereview.dto.CourseReviewES;
import com.example.uolcoursereview.elasticsearch.ElasticSearchQuery;
import com.example.uolcoursereview.model.CourseReview;
import com.example.uolcoursereview.rabbitmq.RabbitMQProducer;
import com.example.uolcoursereview.service.CourseReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    @Autowired
    private RabbitMQProducer producer;

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @GetMapping("/courseReviews")
    public ResponseEntity<List<CourseReview>> getCourseReviews(
            @RequestParam(required = false) String courseCode
    ) {
        CourseReviewQueryParams courseReviewQueryParams = new CourseReviewQueryParams();
        courseReviewQueryParams.setCourseCode(courseCode);

        List<CourseReview> reviewList = courseReviewService.getCourseReviews(courseReviewQueryParams);
        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }

    @GetMapping("/courseReviews/{courseReviewId}")
    public ResponseEntity<CourseReview> getCourseReview(
            @PathVariable Integer courseReviewId
    ) {
        CourseReview courseReview = courseReviewService.getCourseReviewById(courseReviewId);

        if (courseReview != null) {
            return ResponseEntity.status(HttpStatus.OK).body(courseReview);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/courseReview")
    public ResponseEntity<CourseReview> createCourseReview(@RequestBody @Valid CourseReviewRequest courseReviewRequest) throws IOException {

        Integer courseReviewId = courseReviewService.createCourseReview(courseReviewRequest);

        CourseReview courseReview = courseReviewService.getCourseReviewById(courseReviewId);
        System.out.println("mysql generated Id: " + courseReviewId);

        // convert java object to object for elastic search
        CourseReviewES courseReviewES = courseReviewService.convertToEsObject(courseReview);

        // send it to rabbitmq
        producer.sendMessage(courseReviewES);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseReview);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam("keywords") String keywords) throws IOException {
//        producer.sendMessage(keywords);
        List<CourseReviewES> courseReviewESList = elasticSearchQuery.search(keywords);

        return new ResponseEntity<>(courseReviewESList, HttpStatus.OK);
    }

    /*
    @GetMapping("/searchAll")
    public ResponseEntity<Object> searchAll() throws IOException {
        List<CourseReviewES> courseReviewESList = elasticSearchQuery.searchAllDocuments();

        return new ResponseEntity<>(courseReviewESList, HttpStatus.OK);
    }
    */
}
