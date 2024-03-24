package com.example.uolcoursereview.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "course_reviews")
public class CourseReviewES {

    @Id
    Integer id;

    @Field(type = FieldType.Integer, name = "studentId")
    Integer studentId;

    @Field(type = FieldType.Text, name = "courseCode")
    String courseCode;

    @Field(type = FieldType.Integer, name = "rating")
    Integer rating;

    @Field(type = FieldType.Integer, name = "difficulty")
    Integer difficulty;

    @Field(type = FieldType.Integer, name = "workload")
    Integer workload;

    @Field(type = FieldType.Integer, name = "studyHourPerWeek")
    Integer studyHourPerWeek;

    @Field(type = FieldType.Text, name = "review")
    String review;

    public CourseReviewES() {
    }

    public CourseReviewES(Integer id, Integer studentId, String courseCode, Integer rating, Integer difficulty, Integer workload, Integer studyHourPerWeek, String review) {
        this.id = id;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.rating = rating;
        this.difficulty = difficulty;
        this.workload = workload;
        this.studyHourPerWeek = studyHourPerWeek;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Integer getStudyHourPerWeek() {
        return studyHourPerWeek;
    }

    public void setStudyHourPerWeek(Integer studyHourPerWeek) {
        this.studyHourPerWeek = studyHourPerWeek;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
