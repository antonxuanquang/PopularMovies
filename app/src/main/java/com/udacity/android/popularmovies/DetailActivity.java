package com.udacity.android.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.data.AddMovieViewModel;
import com.udacity.android.popularmovies.data.AddMovieViewModelFactory;
import com.udacity.android.popularmovies.data.AppDatabase;
import com.udacity.android.popularmovies.data.AppExecutors;
import com.udacity.android.popularmovies.data.Movie;
import com.udacity.android.popularmovies.tasks.LoadReviewTask;
import com.udacity.android.popularmovies.tasks.LoadTrailerTask;
import com.udacity.android.popularmovies.tasks.ReviewAdapter;
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
    private Button btnAddFavorite;
    private TrailerAdapter trailerAdapter;
    private RecyclerView rvTrailerList;
    private RecyclerView rvReviewList;
    private ReviewAdapter reviewAdapter;
    private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        imgPoster = (ImageView) findViewById(R.id.img_poster);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRating = (TextView) findViewById(R.id.tv_rate);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvOverview = (TextView) findViewById(R.id.tv_overivew);
        rvTrailerList = (RecyclerView) findViewById(R.id.rv_trailer_list);
        rvReviewList = (RecyclerView) findViewById(R.id.rv_review_list);
        btnAddFavorite = (Button) findViewById(R.id.btn_add_favorite);

        trailerAdapter = new TrailerAdapter();
        rvTrailerList.setHasFixedSize(false);
        LinearLayoutManager trailerListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTrailerList.setLayoutManager(trailerListLayoutManager);
        rvTrailerList.setAdapter(trailerAdapter);

        reviewAdapter = new ReviewAdapter();
        rvReviewList.setHasFixedSize(false);
        LinearLayoutManager reviewListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvReviewList.setLayoutManager(reviewListLayoutManager);
        rvReviewList.setAdapter(reviewAdapter);

        Intent intentStartedDetailActivity = getIntent();
        if (intentStartedDetailActivity != null) {
            if (intentStartedDetailActivity.hasExtra(StringUtils.MOVIE_OBJECT_EXTRA)) {
                movie = (Movie) intentStartedDetailActivity.getSerializableExtra(StringUtils.MOVIE_OBJECT_EXTRA);
                setupDatabase(movie.getId());
                setMovieDataToViewComponents();
            }
        }
    }

    private void setupDatabase(Integer movieId) {
        mDb = AppDatabase.getInstance(getApplicationContext());

        AddMovieViewModelFactory addMovieViewModelFactory = new AddMovieViewModelFactory(mDb, movieId);
//        AddMovieViewModel viewModel = ViewModelProviders.of(this, addMovieViewModelFactory)
//                .get(AddMovieViewModel.class);
//        viewModel.getMovie().observe(this, new Observer<Movie>() {
//            @Override
//            public void onChanged(@Nullable Movie movie) {
//                viewModel.getMovie().removeObserver(this);
//                //TODO maybe missing the code to update UI here
//            }
//        });
    }

    private void setMovieDataToViewComponents() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        ImageUtils.loadImageTo(imgPoster, movie.getPosterPath());
        String title = movie.getTitle().equals(movie.getOriginalTitle())
                ? movie.getOriginalTitle()
                : movie.getOriginalTitle() + " (" + movie.getTitle() + ")";
        tvTitle.setText(title);
        tvRating.setText(String.format("%.1f/10", movie.getAverageRating()));
        tvReleaseDate.setText(simpleDateFormat.format(movie.getReleaseDate()));
        tvOverview.setText(movie.getOverview());
        setFavoriteBtnWords(movie.getId());
        loadTrailers();
        loadReviews();
    }

    private void setFavoriteBtnWords(Integer movieId) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mDb.movieDao().loadMovieById(movieId) == null) {
                    btnAddFavorite.setText(getString(R.string.unmark_as_favorite));
                } else {
                    btnAddFavorite.setText(getString(R.string.mark_as_favorite));
                }
            }
        });
    }

    public void onBtnAddFavoriteClick(View view) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "click");
                if (mDb.movieDao().loadMovieById(movie.getId()) == null) {
                    Log.d("TAG", "not found");
                    mDb.movieDao().insertMovie(movie);
                } else {
                    Log.d("TAG", "found");
                    mDb.movieDao().deleteMovie(movie);
                }
            }
        });
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

    private void loadReviews() {
        new LoadReviewTask(this, movie).execute();
    }

    public ReviewAdapter getReviewAdapter() {
        return reviewAdapter;
    }
}
