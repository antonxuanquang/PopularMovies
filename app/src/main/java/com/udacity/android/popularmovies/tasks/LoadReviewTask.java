package com.udacity.android.popularmovies.tasks;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.udacity.android.popularmovies.DetailActivity;
import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.data.MovieReview;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import utils.EspressoIdlingResource;
import utils.MovieDataJsonUtils;
import utils.NetworkUtils;

public class LoadReviewTask extends AsyncTask<Void, Void, List<MovieReview>> {
    private final DetailActivity detailActivity;
    private final Movie movie;

    public LoadReviewTask(DetailActivity detailActivity, Movie movie) {
        this.detailActivity = detailActivity;
        this.movie = movie;
    }

    @Override
    protected List<MovieReview> doInBackground(Void... voids) {
        EspressoIdlingResource.increment(); // App is busy loading new data (for testing only)

        Integer movieId = movie.getId();
        URL api = NetworkUtils.buildMovieReviewsURL(movieId);

        try {
            String jsonResult = NetworkUtils.getResponseFromURL(api);
            List<MovieReview> reviews = MovieDataJsonUtils.parseReviewList(jsonResult, movieId);
            EspressoIdlingResource.decrement();
            return reviews;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<MovieReview> movieReviews) {
        if (movieReviews != null) {
            if (movieReviews.isEmpty()) {
                ConstraintLayout reviewView = (ConstraintLayout) detailActivity.findViewById(R.id.review_view);
                reviewView.setVisibility(View.INVISIBLE);
            } else {
                detailActivity
                        .getReviewAdapter()
                        .setReviews(movieReviews);
            }
        }
    }
}
