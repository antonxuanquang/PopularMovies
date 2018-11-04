package utils;

import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.data.MovieTrailer;
import com.udacity.android.popularmovies.tasks.MovieReview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieDataJsonUtils {

    private final static String TITLE = "title";
    private final static String VOTE_AVERAGE = "vote_average";
    private final static String VOTE_COUNT = "vote_count";
    private final static String POSTER_PATH = "poster_path";
    private final static String BACKDROP_PATH = "backdrop_path";
    private final static String ORIGINAL_TITLE = "original_title";
    private final static String RELEASE_DATE = "release_date";
    private final static String OVERVIEW = "overview";
    private static final String MOVIE_ID = "id";
    private static final String RESULTS = "results";
    private static final String TRAILER_ID = "id";
    private static final String TRAILER_NAME = "name";
    private static final String TRAILER_SITE = "site";
    private static final String TRAILER_KEY = "key";
    private static final String REVIEW_ID = "id";
    private static final String CONTENT = "content";
    private static final String REVIEW_URL = "url";
    private static final String AUTHOR = "author";

    public static List<Movie> parseMovieList(
            String movieListJSONString
    ) throws JSONException, ParseException {

        JSONObject dataJSON = new JSONObject(movieListJSONString);
        JSONArray movieListJSON = dataJSON.getJSONArray(RESULTS);

        List<Movie> movieList = new ArrayList<>();

        for(int i = 0; i < movieListJSON.length(); i++) {
            JSONObject movieJson = movieListJSON.getJSONObject(i);
            Movie movie = parseMovieJson(movieJson);
            movieList.add(movie);
        }

        return movieList;
    }

    private static Movie parseMovieJson(JSONObject movieJSON) throws JSONException, ParseException {
        String title = movieJSON.getString(TITLE);
        String posterPath = movieJSON.getString(POSTER_PATH);
        Integer voteCount = movieJSON.getInt(VOTE_COUNT);
        double voteAverage = movieJSON.getDouble(VOTE_AVERAGE);
        String orignalTitle = movieJSON.getString(ORIGINAL_TITLE);
        String backdropPath = movieJSON.getString(BACKDROP_PATH);
        String releaseDate = movieJSON.getString(RELEASE_DATE);
        String overview = movieJSON.getString(OVERVIEW);
        Integer movieId = movieJSON.getInt(MOVIE_ID);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        return new Movie(
                movieId,
                title,
                posterPath,
                voteCount,
                voteAverage,
                backdropPath,
                overview,
                orignalTitle,
                formatter.parse(releaseDate)
        );
    }

    public static List<MovieTrailer> parseTrailerList(
            String trailersJSONString,
            Integer movieId
    ) throws JSONException {
        JSONObject dataJSON = new JSONObject(trailersJSONString);
        JSONArray trailersListJSON = dataJSON.getJSONArray(RESULTS);
        List<MovieTrailer> trailerList = new ArrayList<>();

        for(int i = 0; i < trailersListJSON.length(); i++) {
            JSONObject trailerJson = trailersListJSON.getJSONObject(i);
            MovieTrailer trailer = parseTrailerJson(trailerJson, movieId);
            trailerList.add(trailer);
        }

        return trailerList;
    }

    private static MovieTrailer parseTrailerJson(JSONObject trailerJson, Integer movieId) throws JSONException {
        String trailerId = trailerJson.getString(TRAILER_ID);
        String name = trailerJson.getString(TRAILER_NAME);
        String site = trailerJson.getString(TRAILER_SITE);
        String key = trailerJson.getString(TRAILER_KEY);

        return new MovieTrailer(
                trailerId,
                movieId,
                name,
                site,
                key
        );
    }

    public static List<MovieReview> parseReviewList(String reviewListJson, Integer movieId) throws JSONException {
        JSONObject dataJSON = new JSONObject(reviewListJson);
        JSONArray reviewsListJSON = dataJSON.getJSONArray(RESULTS);
        List<MovieReview> reviewList = new ArrayList<>();

        for(int i = 0; i < reviewsListJSON.length(); i++) {
            JSONObject reviewJson = reviewsListJSON.getJSONObject(i);
            MovieReview review = parseReviewJson(reviewJson, movieId);
            reviewList.add(review);
        }

        return reviewList;
    }

    private static MovieReview parseReviewJson(JSONObject reviewJson, Integer movieId) throws JSONException {

        String reviewId = reviewJson.getString(REVIEW_ID);
        String content = reviewJson.getString(CONTENT);
        String reviewUrl = reviewJson.getString(REVIEW_URL);
        String author = reviewJson.getString(AUTHOR);

        return new MovieReview(
                reviewId,
                movieId,
                content,
                reviewUrl,
                author
        );
    }
}
