package com.udacity.android.popularmovies.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.android.popularmovies.R;
import com.udacity.android.popularmovies.data.MovieTrailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapterViewHolder> {
    private static final int WITHOUT_BRAKE_BAR = 0;
    private static final int WITH_BRAKE_BAR = 1;
    private Context context;
    private List<MovieTrailer> trailerList;

    @NonNull
    @Override
    public TrailerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layout;
        if (viewType == WITH_BRAKE_BAR) {
            layout = R.layout.trailer_list_item;
        } else {
            layout = R.layout.trailer_list_item_without_bar;
        }
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout, parent, false);

        return new TrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapterViewHolder holder, int position) {
        MovieTrailer trailer = trailerList.get(position);
        holder.setTrailer(trailer);
        holder.setContext(context);
        holder.getTVTrailerName().setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        if (trailerList == null) return 0;
        return trailerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WITHOUT_BRAKE_BAR;
        } else {
            return WITH_BRAKE_BAR;
        }
    }

    public void setTrailers(List<MovieTrailer> movieTrailers) {
        this.trailerList = movieTrailers;
        notifyDataSetChanged();
    }
}
