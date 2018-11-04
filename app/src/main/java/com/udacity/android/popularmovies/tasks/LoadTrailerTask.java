package com.udacity.android.popularmovies.tasks;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udacity.android.popularmovies.DetailActivity;
import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.data.MovieTrailer;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import utils.EspressoIdlingResource;
import utils.MovieDataJsonUtils;
import utils.NetworkUtils;

public class LoadTrailerTask extends AsyncTask<Void, Void, List<MovieTrailer>> {
    private Movie movie;
    private DetailActivity detailActivity;

    public LoadTrailerTask(DetailActivity detailActivity, Movie movie) {
        this.detailActivity = detailActivity;
        this.movie = movie;
    }

    @Override
    protected List<MovieTrailer> doInBackground(Void... voids) {
        EspressoIdlingResource.increment(); // App is busy loading new data (for testing only)

        Integer movieId = movie.getId();
        URL api = NetworkUtils.buildMovieTrailersURL(movieId);

        try {
            String jsonResult = NetworkUtils.getResponseFromURL(api);
            List<MovieTrailer> trailers = MovieDataJsonUtils.parseTrailerList(jsonResult, movieId);
            EspressoIdlingResource.decrement();
            return trailers;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MovieTrailer> movieTrailers) {
        if (movieTrailers != null) {
            if (movieTrailers.isEmpty()) {
                ConstraintLayout trailerView = (ConstraintLayout) detailActivity.findViewById(R.id.trailer_view);
                trailerView .setVisibility(View.INVISIBLE);
            } else {
                detailActivity.getTrailerAdapter().addTrailers(movieTrailers);
            }
        }
    }
}
