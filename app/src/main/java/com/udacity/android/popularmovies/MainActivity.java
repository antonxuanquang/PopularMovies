package com.udacity.android.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.android.popularmovies.data.FavoriteMoviesViewModel;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.tasks.LoadMovieTask;
import com.udacity.android.popularmovies.tasks.MovieAdapter;

import java.util.List;

import utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN_COUNT = 2;
    private RecyclerView rvMovieList;
    private MovieAdapter movieAdapter;
    private String userReference;
    private int page;
    private List<Movie> favoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = 0;

        rvMovieList = (RecyclerView) findViewById(R.id.rv_movie_list);
        userReference = StringUtils.MOST_POPULAR;

        movieAdapter = new MovieAdapter();
        rvMovieList.setAdapter(movieAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMN_COUNT);
        rvMovieList.setLayoutManager(gridLayoutManager);

        rvMovieList.setHasFixedSize(false);

        addScrollListener(rvMovieList);

        FavoriteMoviesViewModel favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);
        favoriteMoviesViewModel.getMovies().observe(this, (movies) -> {
            favoriteMovies = movies;
            loadMovie();
        });

        loadMovie();
    }

    private void addScrollListener(RecyclerView rvMovieList) {
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1)
                    && !userReference.equals(getString(R.string.favorite))) {
                loadMovie();
            }
            }
        });
    }

    public void loadMovie() {
        if (userReference.equals(StringUtils.MOST_POPULAR)
                || userReference.equals(StringUtils.TOP_RATED)) {
            page++;
            Log.d(this.getLocalClassName(), String.format("Load Movie with page %d", page));
            new LoadMovieTask(this, page).execute(userReference);
        } else if (userReference.equals(getString(R.string.favorite))){
            movieAdapter.setMovies(favoriteMovies);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String newReference = null;
        if (item.getItemId() == R.id.action_popular) {
            newReference = StringUtils.MOST_POPULAR;
        } else if (item.getItemId() == R.id.action_top_rated) {
            newReference = StringUtils.TOP_RATED;
        } else if (item.getItemId() == R.id.action_favorite) {
            newReference = getString(R.string.favorite);
        }

        if (!newReference.equals(userReference)) {
            resetView(newReference);
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetView(String newReference) {
        userReference = newReference;
        movieAdapter.resetData();
        page = 0;
        loadMovie();
    }

    public MovieAdapter getMovieAdapter() {
        return movieAdapter;
    }
}
