package com.wipro.MovieReviewService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReviewEntity {

    @Id
    @GeneratedValue
    private Long reviewId;
    private String userName;
    private String movieName;
    private String reviewStar;

    public ReviewEntity() {
        super();
    }

    public ReviewEntity(String userName, String movieName, String reviewStar) {
        this.userName = userName;
        this.movieName = movieName;
        this.reviewStar = reviewStar;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(String reviewStar) {
        this.reviewStar = reviewStar;
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "reviewId=" + reviewId +
                ", userName='" + userName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", reviewStar='" + reviewStar + '\'' +
                '}';
    }
}
