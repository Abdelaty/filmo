package com.example.filmo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmo.DetailedActivity;
import com.example.filmo.Model.movies.Result;
import com.example.filmo.R;
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

    public MoviesAdapter(Context context, List<Result> List) {
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
                .listener(GlidePalette.with("http://image.tmdb.org/t/p/w500" + imageUrl)
                        .use(GlidePalette.Profile.MUTED_DARK)
                        .intoBackground(holder.relativeLayout)
                        .intoTextColor(holder.movieTitleMain_tv)
                        .use(GlidePalette.Profile.VIBRANT)
                        .intoBackground(holder.relativeLayout, GlidePalette.Swatch.RGB)
                        .intoTextColor(holder.movieCategoryMain_tv, GlidePalette.Swatch.BODY_TEXT_COLOR)
                        .crossfade(true)
                ).into(holder.moviePosterMain_iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(context, DetailedActivity.class);
                myIntent.putExtra("TITLE_KEY", moviesList.get(position).getTitle());
                myIntent.putExtra("LANGUAGE_KEY", moviesList.get(position).getOriginalLanguage());
                myIntent.putExtra("OVERVIEW_KEY", moviesList.get(position).getOverview());
                myIntent.putExtra("RELEASE_KEY", moviesList.get(position).getReleaseDate());
                myIntent.putExtra("POSTER_KEY", moviesList.get(position).getPosterPath());
                myIntent.putExtra("AVG_RATE_KEY", moviesList.get(position).getVoteAverage());
                myIntent.putExtra("VOTE_COUNT_KEY", moviesList.get(position).getVoteCount());
//                myIntent.putExtra("GENRE_KEY", (Parcelable) moviesList.get(position).getGenreIds());
                myIntent.putExtra("IS_ADULT", moviesList.get(position).getAdult());
                myIntent.putExtra("IS_VIDEO", moviesList.get(position).getVideo());
                myIntent.putExtra("MOVIE_ID", moviesList.get(position).getId());

                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title_main)
        TextView movieTitleMain_tv;
        Intent intent = new Intent(itemView.getContext(), DetailedActivity.class);


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
