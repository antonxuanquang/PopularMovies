package com.udacity.android.popularmovies.data;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class AddMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final AppDatabase mDb;
    private final Integer movieId;

    public AddMovieViewModelFactory(AppDatabase mDb, Integer movieId) {
        this.mDb = mDb;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddMovieViewModel(mDb, movieId);
    }
}
