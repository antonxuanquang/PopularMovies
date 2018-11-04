package com.udacity.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.android.popularmovies.tasks.LoadMovieTask;
import com.udacity.android.popularmovies.tasks.MovieAdapter;

import utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private static final int COLUMN_COUNT = 2;
    private RecyclerView rvMovieList;
    private MovieAdapter movieAdapter;
    private String userReference;
    private int page;

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

        loadMovie();
    }

    private void addScrollListener(RecyclerView rvMovieList) {
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    loadMovie();
                }
            }
        });
    }

    public void loadMovie() {
        page++;
        new LoadMovieTask(this, page).execute(userReference);
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
