package com.udacity.android.popularmovies;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.android.popularmovies.data.MainViewModel;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.data.MovieRepo;
import com.udacity.android.popularmovies.tasks.LoadMovieTask;
import com.udacity.android.popularmovies.tasks.MovieAdapter;

import java.util.List;

import utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN_COUNT = 2;
    private RecyclerView rvMovieList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovieList = (RecyclerView) findViewById(R.id.rv_movie_list);

        movieAdapter = new MovieAdapter();
        rvMovieList.setAdapter(movieAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMN_COUNT);
        rvMovieList.setLayoutManager(gridLayoutManager);

        rvMovieList.setHasFixedSize(false);

        addScrollListener(rvMovieList);

        MainViewModel favoriteMoviesViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        favoriteMoviesViewModel.getMovies().observe(this, (movies) -> {
            movieAdapter.setMovies(movies);
        });
    }

    private void addScrollListener(RecyclerView rvMovieList) {
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    MovieRepo.getInstance().loadMovies();
                }
            }
        });
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
            MovieRepo.getInstance().setUserReference(StringUtils.MOST_POPULAR);
        } else if (item.getItemId() == R.id.action_top_rated) {
            MovieRepo.getInstance().setUserReference(StringUtils.TOP_RATED);
        } else if (item.getItemId() == R.id.action_favorite) {
            MovieRepo.getInstance().setUserReference(StringUtils.FAVORITE);
            MovieRepo.getInstance().loadFavoriteMovies(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
