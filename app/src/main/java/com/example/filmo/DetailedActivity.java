package com.example.filmo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.filmo.Model.Cast;
import com.example.filmo.Model.CastResult;
import com.example.filmo.Model.ResultReview;
import com.example.filmo.Model.ResultTrailer;
import com.example.filmo.Model.Reviews;
import com.example.filmo.Model.Trailers;
import com.example.filmo.Network.GetDataService;
import com.example.filmo.Network.RetrofitClientInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    public List<CastResult> castMoviesList;
    String movieId, originalLanguage, overview, releaseDate, posterUrl, title;
    boolean isVideo, isAdult;
    int voteCount;
    double rate;
    private List<ResultReview> reviewMoviesList;
    private List<ResultTrailer> trailersMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        String apiKey = "ceb888b71023afda704f84975d2642b5";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        isAdult = intent.getBooleanExtra("IS_ADULT", false);
        title = intent.getStringExtra("TITLE_KEY");
        isVideo = intent.getBooleanExtra("IS_VIDEO", false);
        voteCount = intent.getIntExtra("VOTE_COUNT_KEY", 0);
        rate = intent.getDoubleExtra("AVG_RATE_KEY", 0);
        posterUrl = "http://image.tmdb.org/t/p/w185" + intent.getStringExtra("POSTER_KEY");
        releaseDate = intent.getStringExtra("RELEASE_KEY");
        overview = intent.getStringExtra("OVERVIEW_KEY");
        originalLanguage = intent.getStringExtra("LANGUAGE_KEY");
        movieId = intent.getStringExtra("MOVIE_ID");
//
//        String REVIEW_URL = " https://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=" + apiKey;
//        String CAST_URL = "http://api.themoviedb.org/3/movie/" + movieId + "/casts?api_key=" + apiKey;
//        String TRAILER_URL = " https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + apiKey;


        generateReviewsCall();
        generateCastCall();
        generateTrailersCall();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void generateReviewsCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Reviews> call = service.getReviews(movieId);
        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(@NonNull Call<Reviews> call, @NonNull Response<Reviews> response) {
                reviewMoviesList = response.body().getResults();
                Log.v("Reviews Call", "Success" + reviewMoviesList.get(1).getUrl());
            }

            @Override
            public void onFailure(@NonNull Call<Reviews> call, @NonNull Throwable t) {
                Log.v("Reviews Call", t.getMessage());
            }
        });


    }

    public void generateCastCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Cast> call = service.getCast(movieId);
        call.enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(@NonNull Call<Cast> call, @NonNull Response<Cast> response) {
                castMoviesList = response.body().getCast();
                Log.v("Cast Call", "Success" + castMoviesList.get(0).getCharacter());
                Log.v("Cast Call", "Success");

            }

            @Override
            public void onFailure(@NonNull Call<Cast> call, @NonNull Throwable t) {
                Log.v("Cast Call", t.getMessage());
            }
        });


    }

    public void generateTrailersCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Trailers> call = service.getTrailer(movieId);
        call.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(@NonNull Call<Trailers> call, @NonNull Response<Trailers> response) {
                trailersMoviesList = response.body().getResults();
                Log.v("Trailers Call", "Success");
            }

            @Override
            public void onFailure(@NonNull Call<Trailers> call, @NonNull Throwable t) {
                Log.v("Trailers Call", t.getMessage());
            }
        });


    }
}
