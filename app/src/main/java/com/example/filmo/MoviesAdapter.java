package com.example.filmo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmo.Model.Result;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Context context;
    private List<Result> moviesList;
    private LayoutInflater layoutInflater;

    MoviesAdapter(Context context, List<Result> List) {
        this.context = context;
        moviesList = List;
        Log.v("hello", moviesList.get(0).getOverview());

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.movie_item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String movieTitle = moviesList.get(position).getTitle();
        holder.movieTitleMain_tv.setText(movieTitle);
        holder.movieCategoryMain_tv.setText("Generals");
        String imageUrl = moviesList.get(position).getPosterPath();
        Glide.with(context).load("http://image.tmdb.org/t/p/w185" + imageUrl)
                .listener(GlidePalette.with("http://image.tmdb.org/t/p/w185" + imageUrl)
                        .use(GlidePalette.Profile.MUTED_DARK)
                        .intoBackground(holder.relativeLayout)
                        .intoTextColor(holder.movieTitleMain_tv)
                        .use(GlidePalette.Profile.VIBRANT)
                        .intoBackground(holder.relativeLayout, GlidePalette.Swatch.RGB)
                        .intoTextColor(holder.movieCategoryMain_tv, GlidePalette.Swatch.BODY_TEXT_COLOR)
                        .crossfade(true)
                ).into(holder.moviePosterMain_iv);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title_main)
        TextView movieTitleMain_tv;

        @BindView(R.id.movie_category_main)
        TextView movieCategoryMain_tv;

        @BindView(R.id.movie_poster_main)
        ImageView moviePosterMain_iv;

        @BindView(R.id.main_text_lay)
        RelativeLayout relativeLayout;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {

            });
        }

    }
}