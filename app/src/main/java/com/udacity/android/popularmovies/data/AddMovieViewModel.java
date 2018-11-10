package com.udacity.android.popularmovies.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class AddMovieViewModel extends ViewModel {

    private final LiveData<Movie> movie;

    public AddMovieViewModel(AppDatabase mDb, Integer movieId) {
        movie = mDb.movieDao().loadMovieById(movieId);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }
}
