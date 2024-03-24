package com.example.uolcoursereview.dto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CourseReviewRequest implements Serializable {

    @NotNull
    Integer studentId;
    @NotNull
    String courseCode;
    @NotNull

    Integer rating;
    @NotNull

    Integer difficulty;
    @NotNull

    Integer workload;
    @NotNull

    Integer studyHourPerWeek;
    @NotNull

    String review;


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
