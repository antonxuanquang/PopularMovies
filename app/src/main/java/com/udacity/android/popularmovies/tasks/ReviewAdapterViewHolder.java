package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.MovieReview;

import utils.NetworkUtils;

class ReviewAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvAuthor;
    private TextView tvReview;
    private MovieReview review;
    private Context context;

    public ReviewAdapterViewHolder(View itemView) {
        super(itemView);
        tvReview = (TextView) itemView.findViewById(R.id.tv_review);
        tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
        tvReview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Uri reviewUri = NetworkUtils.buildReviewUri(review.getReviewUrl());
        Intent viewReviewIntent = new Intent(Intent.ACTION_VIEW, reviewUri);
        context.startActivity(viewReviewIntent);
    }

    public TextView getTvAuthor() {
        return tvAuthor;
    }

    public TextView getTvReview() {
        return tvReview;
    }

    public void setMovieReview(MovieReview review) {
        this.review = review;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
