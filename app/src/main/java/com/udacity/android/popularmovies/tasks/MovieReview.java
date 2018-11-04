package com.udacity.android.popularmovies.tasks;

public class MovieReview {

    private String reviewId;
    private Integer movieId;
    private String content;
    private String reviewUrl;
    private String author;

    public MovieReview(String reviewId, Integer movieId, String content, String reviewUrl, String author) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.content = content;
        this.reviewUrl = reviewUrl;
        this.author = author;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
