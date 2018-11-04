package com.udacity.android.popularmovies.data;

public class MovieTrailer {

    private String name;
    private String site;
    private String key;
    private String trailerId;
    private Integer movidId;

    public MovieTrailer(
            String trailerId, Integer movieId, String name, String site, String key
    ) {
        this.trailerId = trailerId;
        this.movidId = movieId;
        this.name = name;
        this.site = site;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public Integer getMovidId() {
        return movidId;
    }

    public void setMovidId(Integer movidId) {
        this.movidId = movidId;
    }
}
