package utils;

import com.udacity.android.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieDataJsonUtils {
    public static List<Movie> parse(
            String movieListJSONString
    ) throws JSONException, ParseException {

        final String MOVIE_LIST = "results";

        JSONObject dataJSON = new JSONObject(movieListJSONString);
        JSONArray movieListJSON = dataJSON.getJSONArray(MOVIE_LIST);

        List<Movie> movieList = new ArrayList<>();

        for(int i = 0; i < movieListJSON.length(); i++) {
            JSONObject movieJson = movieListJSON.getJSONObject(i);
            Movie movie = parseMovieJson(movieJson);
            movieList.add(movie);
        }

        return movieList;
    }

    private static Movie parseMovieJson(JSONObject movieJSON) throws JSONException, ParseException {
        final String TITLE = "title";
        final String VOTE_AVERAGE = "vote_average";
        final String VOTE_COUNT = "vote_count";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";
        final String ORIGINAL_TITLE = "original_title";
        final String RELEASE_DATE = "release_date";
        final String OVERVIEW = "overview";

        String title = movieJSON.getString(TITLE);
        String posterPath = movieJSON.getString(POSTER_PATH);
        Integer voteCount = movieJSON.getInt(VOTE_COUNT);
        double voteAverage = movieJSON.getDouble(VOTE_AVERAGE);
        String orignalTitle = movieJSON.getString(ORIGINAL_TITLE);
        String backdropPath = movieJSON.getString(BACKDROP_PATH);
        String releaseDate = movieJSON.getString(RELEASE_DATE);
        String overview = movieJSON.getString(OVERVIEW);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        return new Movie(
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
}
