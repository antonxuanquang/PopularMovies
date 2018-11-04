package com.udacity.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.tasks.LoadTrailerTask;
import com.udacity.android.popularmovies.tasks.TrailerAdapter;

import java.text.SimpleDateFormat;

import utils.ImageUtils;
import utils.StringUtils;

public class DetailActivity extends AppCompatActivity {

    private Movie movie;
    private ImageView imgPoster;
    private TextView tvTitle;
    private TextView tvRating;
    private TextView tvReleaseDate;
    private TextView tvOverview;

    private TrailerAdapter trailerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPoster = (ImageView) findViewById(R.id.img_poster);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRating = (TextView) findViewById(R.id.tv_rate);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvOverview = (TextView) findViewById(R.id.tv_overivew);
        trailerAdapter = new TrailerAdapter();

        Intent intentStartedDetailActivity = getIntent();
        if (intentStartedDetailActivity != null) {
            if (intentStartedDetailActivity.hasExtra(StringUtils.MOVIE_OBJECT_EXTRA)) {
                movie = (Movie) intentStartedDetailActivity.getSerializableExtra(StringUtils.MOVIE_OBJECT_EXTRA);
                setMovieDataToViewComponents();
            }
        }
    }

    private void setMovieDataToViewComponents() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        ImageUtils.loadImageTo(imgPoster, movie.getPosterPath());
        String title = movie.getTitle().equals(movie.getOriginalTitle())
                ? movie.getOriginalTitle()
                : movie.getOriginalTitle() + " (" + movie.getTitle() + ")";
        tvTitle.setText(title);
        tvRating.setText(String.format("%f/10", movie.getAverageRating()));
        tvReleaseDate.setText(simpleDateFormat.format(movie.getReleaseDate()));
        tvOverview.setText(movie.getOverview());
        loadTrailers();
    }

    public TrailerAdapter getTrailerAdapter() {
        return trailerAdapter;
    }

    public void setTrailerAdapter(TrailerAdapter trailerAdapter) {
        this.trailerAdapter = trailerAdapter;
    }

    private void loadTrailers() {
        new LoadTrailerTask(this, movie).execute();
    }
}
