package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.android.popularmovies.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapterViewHolder> {
    private static final int HUNDRED = 100;
    private List<MovieReview> movieReviews;
    private Context context;

    @NonNull
    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layout = R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);

        return new ReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapterViewHolder holder, int position) {
        MovieReview review = movieReviews.get(position);
        holder.getTvAuthor().setText(review.getAuthor());
        setMovieReview(holder, review);
        holder.setContext(context);
        holder.setMovieReview(review);
    }

    private void setMovieReview(@NonNull ReviewAdapterViewHolder holder, MovieReview review) {
        String shortenReview;
        String orginalReview = review.getContent();
        if (orginalReview.length() > HUNDRED) {
            shortenReview = String.format("%s...more", orginalReview.substring(0, HUNDRED));
        } else {
            shortenReview = orginalReview;
        }
        holder.getTvReview().setText(shortenReview);
    }

    @Override
    public int getItemCount() {
        if (movieReviews == null) return 0;
        return movieReviews.size();
    }

    public void setReviews(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
        notifyDataSetChanged();
    }
}
