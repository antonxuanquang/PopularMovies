package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.udacity.android.popularmovies.DetailActivity;
import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.Movie;

import utils.StringUtils;

public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

    private ImageView posterImageView;
    private Movie movie;
    private Context context;

    public MovieAdapterViewHolder(View itemView) {
        super(itemView);
        posterImageView = (ImageView) itemView.findViewById(R.id.img_poster);
        itemView.setOnClickListener(this);
    }

    public ImageView getPosterImageView() {
        return posterImageView;
    }

    public void setPosterImageView(ImageView posterImageView) {
        this.posterImageView = posterImageView;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Class destinationClass = DetailActivity.class;
        Intent goToDetailActivity = new Intent(context, destinationClass);
        goToDetailActivity.putExtra(StringUtils.MOVIE_OBJECT_EXTRA, movie);
        context.startActivity(goToDetailActivity);
    }
}
