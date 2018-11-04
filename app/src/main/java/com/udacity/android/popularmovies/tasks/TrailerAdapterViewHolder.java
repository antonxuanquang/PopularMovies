package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udacity.android.popularmovies.data.MovieTrailer;

class TrailerAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private MovieTrailer trailer;
    private Context context;

    public TrailerAdapterViewHolder(View itemView) {
        super(itemView);
    }
    public MovieTrailer getTrailer() {
        return trailer;
    }

    public void setTrailer(MovieTrailer trailer) {
        this.trailer = trailer;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }
}
