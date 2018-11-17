package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.Movie;

import java.util.ArrayList;
import java.util.List;

import utils.ImageUtils;
import utils.StringUtils;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapterViewHolder> {

    private List<Movie> movieList;
    private Context context;

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layout = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layout, parent, shouldAttachToParentImmediately);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder viewHolder, int position) {
        Movie data = movieList.get(position);
        viewHolder.setMovie(data);
        viewHolder.setContext(context);
        ImageUtils.loadImageTo(viewHolder.getPosterImageView(), data.getPosterPath());
    }


    @Override
    public int getItemCount() {
        if (this.movieList == null) return 0;
        return this.movieList.size();
    }

//    public void addMovies(List<Movie> newMovies) {
//        if (this.movieList == null) {
//            this.movieList = new ArrayList<Movie>(newMovies);
//        } else {
//            this.movieList.addAll(newMovies);
//        }
//        notifyDataSetChanged();
//    }
//
//    public void resetData() {
//        movieList = new ArrayList<>();
//    }

    public void setMovies(List<Movie> movies) {
        if(movies != null) {
            movieList = new ArrayList<>(movies);
            notifyDataSetChanged();
        }
    }
}
