package com.udacity.android.popularmovies.tasks;

import android.os.AsyncTask;

import com.udacity.android.popularmovies.MainActivity;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.data.MovieRepo;

import java.net.URL;
import java.util.List;

import utils.EspressoIdlingResource;
import utils.MovieDataJsonUtils;
import utils.NetworkUtils;

public class LoadMovieTask extends AsyncTask<String, Void, List<Movie>> {

    private int page;

    public LoadMovieTask(int page) {
        this.page = page;
    }

    @Override
    protected List<Movie> doInBackground(String... userReferences) {
        EspressoIdlingResource.increment(); // App is busy loading new data (for testing only)
        if(userReferences == null || userReferences.length == 0) {
            return null;
        }

        String userReference = userReferences[0];
        URL api = NetworkUtils.buildMovieListURL(userReference, page);
        try {

            String jsonResult = NetworkUtils.getResponseFromURL(api);

            List<Movie> movieList = MovieDataJsonUtils.parseMovieList(jsonResult);

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
            MovieRepo.getInstance().addMovies(movieData);
        }
    }
}
