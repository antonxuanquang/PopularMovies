package com.udacity.android.popularmovies.tasks;

import android.os.AsyncTask;

import com.udacity.android.popularmovies.MainActivity;
import com.udacity.android.popularmovies.data.Movie;

import java.net.URL;
import java.util.List;

import utils.EspressoIdlingResource;
import utils.MovieDataJsonUtils;
import utils.NetworkUtils;

public class LoadMovieTask extends AsyncTask<String, Void, List<Movie>> {

    private MainActivity mainActivity;
    private int page;

    public LoadMovieTask(MainActivity mainActivity, int page) {
        this.mainActivity = mainActivity;
        this.page = page;
    }

    @Override
    protected List<Movie> doInBackground(String... userReferences) {
        EspressoIdlingResource.increment(); // App is busy loading new data (for testing only)
        if(userReferences == null || userReferences.length == 0) {
            return null;
        }

        String userReference = userReferences[0];
        URL api = NetworkUtils.buildURL(userReference, page);
        try {

            String jsonResult = NetworkUtils.getResponseFromURL(api);

            List<Movie> movieList = MovieDataJsonUtils.parse(jsonResult);

            EspressoIdlingResource.decrement(); // Set app as idle (for testing only)
            return movieList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movieData) {
        if (movieData != null) {
            mainActivity.getMovieAdapter().addMovies(movieData);
        }
    }
}
