package com.udacity.android.popularmovies.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Movie>> getMovies() {
        return MovieRepo.getInstance().getMovies();
    }
}
