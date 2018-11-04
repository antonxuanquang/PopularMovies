package com.udacity.android.popularmovies.data;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {

    private Integer id;
    private String title;
    private String posterPath;
    private Double averageRating;
    private String backdropPath;
    private String overview;
    private String originalTitle;
    private Date releaseDate;
    private Integer voteCount;
    public Movie(
            Integer id,
            String title,
            String posterPath,
            Integer voteCount,
            Double averageRating,
            String backdropPath,
            String overview,
            String originalTitle,
            Date releaseDate
    ) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.voteCount = voteCount;
        this.averageRating = averageRating;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
