package com.udacity.android.popularmovies.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.android.popularmovies.tasks.LoadMovieTask;

import java.util.ArrayList;
import java.util.List;

import utils.StringUtils;

public class MovieRepo {

    private String userReference;
    private int page;
    private static MovieRepo instance;
    private MutableLiveData<List<Movie>> movies;

    private MovieRepo() {
        userReference = StringUtils.MOST_POPULAR;
        page = 0;
        movies = new MutableLiveData<>();
        movies.postValue(new ArrayList<>());
        loadMovies();
    }

    public static MovieRepo getInstance() {
        if (instance == null) {
            synchronized (MovieRepo.class) {
                if(instance == null) {
                    instance = new MovieRepo();
                }
            }
        }
        return instance;
    }

    public void loadMovies() {
        if(userReference.equals(StringUtils.MOST_POPULAR)) {
            new LoadMovieTask(++page).execute(userReference);
        } else if (userReference.equals(StringUtils.TOP_RATED)) {
            new LoadMovieTask(++page).execute(userReference);
        } else if (userReference.equals(StringUtils.FAVORITE)) {
        }
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }


    public void setUserReference(String userReference) {
        resetView(userReference);
    }

    private void resetView(String newReference) {
        if (!userReference.equals(newReference)) {
            userReference = newReference;
            page = 0;
            movies.postValue(new ArrayList<>());
            loadMovies();
        }
    }

    public void addMovies(@NonNull List<Movie> movieData) {
        List<Movie> newList = movies.getValue();
        newList.addAll(movieData);
        movies.postValue(newList);
        Log.d(this.getClass().getName(), String.format("Current movie size: %d", newList.size()));
    }

    public void loadFavoriteMovies(Context mainActivity) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Movie> favoriteMovies = AppDatabase.getInstance(mainActivity)
                    .movieDao()
                    .loadAllMovies();
            movies.postValue(favoriteMovies);
        });
    }
}
