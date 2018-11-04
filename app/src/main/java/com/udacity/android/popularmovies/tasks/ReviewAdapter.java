package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.android.popularmovies.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter {
    private static final int WITHOUT_BRAKE_BAR = 0;
    private static final int WITH_BRAKE_BAR = 1;
    private List<MovieReview> movieReviews;
    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layout;
        if (viewType == WITH_BRAKE_BAR) {
            layout = R.layout.review_list_item;
        } else {
            layout = R.layout.review_list_item_without_bar;
        }
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout, parent, false);

        return new TrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (movieReviews == null) return 0;
        return movieReviews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WITHOUT_BRAKE_BAR;
        } else {
            return WITH_BRAKE_BAR;
        }
    }

    public void setReviews(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
        notifyDataSetChanged();
    }
}
