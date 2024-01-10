package com.example.uolcoursereview;

public class CourseReview {

    Integer id;

    String courseCode;

    Integer rating;

    Integer difficulty;

    Integer workload;

    Integer studyHourPerWeek;

    String review;

    public CourseReview() {
    }

    public CourseReview(Integer id, String courseCode, Integer rating, Integer difficulty, Integer workload, Integer studyHourPerWeek, String review) {
        this.id = id;
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
