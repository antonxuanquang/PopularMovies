package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.MovieTrailer;

import utils.NetworkUtils;

class TrailerAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView playIcon;
    private MovieTrailer trailer;
    private Context context;
    private TextView tvTrailerName;

    public TrailerAdapterViewHolder(View itemView) {
        super(itemView);
        tvTrailerName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
        playIcon = (ImageView) itemView.findViewById(R.id.trailer_icon);
        playIcon.setOnClickListener(this);
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
        Uri trailerUri = NetworkUtils.buildYoutubeTrailerUri(trailer);
        Intent goToYouTube = new Intent(Intent.ACTION_VIEW, trailerUri);
        context.startActivity(goToYouTube);
    }

    public TextView getTVTrailerName() {
        return tvTrailerName;
    }
}
