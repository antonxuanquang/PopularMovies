package com.udacity.android.popularmovies.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> movies;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        movies = database.movieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
