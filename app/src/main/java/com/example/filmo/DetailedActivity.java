package com.example.filmo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.filmo.Adapters.ReviewAdapter;
import com.example.filmo.Adapters.TrailerAdapter;
import com.example.filmo.Model.backdrops.BackdropResult;
import com.example.filmo.Model.backdrops.Backdrops;
import com.example.filmo.Model.cast.Cast;
import com.example.filmo.Model.cast.CastResult;
import com.example.filmo.Model.reviews.ResultReview;
import com.example.filmo.Model.reviews.Reviews;
import com.example.filmo.Model.trailer.ResultTrailer;
import com.example.filmo.Model.trailer.Trailers;
import com.example.filmo.Network.GetDataService;
import com.example.filmo.Network.RetrofitClientInstance;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    public List<CastResult> castMoviesList;
    String originalLanguage, overview, releaseDate, posterUrl, title;
    boolean isVideo, isAdult;
    int voteCount, movieId;
    double rate;
    String apiKey = "ceb888b71023afda704f84975d2642b5";
    @BindView(R.id.detailed_poster)
    ImageView detailed_iv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_overview)
    TextView movieOverview;
    @BindView(R.id.movie_title_detail)
    TextView movieTitle;
    @BindView(R.id.detail_rate)
    TextView movieRate;
    @BindView(R.id.adult_icon)
    ImageView adultIcon;
    @BindView(R.id.release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_poster_detail)
    ImageView detailedRightPoster;
    @BindView(R.id.reviews_rv)
    RecyclerView reviews_rv;
    @BindView(R.id.review_lbl)
    TextView reviewLabel;
    @BindView(R.id.trailer_label)
    TextView trailerLabel;
    @BindView(R.id.trailer_rv)
    RecyclerView trailer_rv;
    Intent intent;
    private List<ResultTrailer> trailersMoviesList;
    private List<ResultReview> reviewMoviesList;
    private List<BackdropResult> backdropsList;
    private ReviewAdapter reviewsAdapter;
    private TrailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        setTitle("");
        getIntentData(intent);
        generateBackdropCall();
        generateReviewsCall();
        generateCastCall();
        generateTrailersCall();
        setLayoutData();


    }

    private void setLayoutData() {
        movieOverview.setText(intent.getStringExtra("OVERVIEW_KEY"));
        movieTitle.setText(intent.getStringExtra("TITLE_KEY"));
        movieRate.setText(Double.toString(intent.getDoubleExtra("AVG_RATE_KEY", 0.0)));
        movieReleaseDate.setText("Release Date: " + intent.getStringExtra("RELEASE_KEY"));
        if (intent.getBooleanExtra("IS_ADULT", false)) {
            adultIcon.setVisibility(View.VISIBLE);
        } else {
            adultIcon.setVisibility(View.GONE);
        }
        Glide.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500" + intent.getStringExtra("POSTER_KEY"))
                .apply(new RequestOptions().override(Target.SIZE_ORIGINAL).dontTransform()).into(detailedRightPoster);
    }

    private void getIntentData(Intent intent) {
        isAdult = intent.getBooleanExtra("IS_ADULT", false);
        title = intent.getStringExtra("TITLE_KEY");
        isVideo = intent.getBooleanExtra("IS_VIDEO", false);
        voteCount = intent.getIntExtra("VOTE_COUNT_KEY", 0);
        rate = intent.getDoubleExtra("AVG_RATE_KEY", 0);
        posterUrl = "http://image.tmdb.org/t/p/w185" + intent.getStringExtra("POSTER_KEY");
        releaseDate = intent.getStringExtra("RELEASE_KEY");
        overview = intent.getStringExtra("OVERVIEW_KEY");
        originalLanguage = intent.getStringExtra("LANGUAGE_KEY");
        movieId = intent.getIntExtra("MOVIE_ID", 0);
    }

    public void generateReviewsCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Reviews> call = service.getReviews(movieId);
        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(@NonNull Call<Reviews> call, @NonNull Response<Reviews> response) {
                reviewMoviesList = response.body().getResults();
                generateReviewsList(reviewMoviesList);
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
                generateTrailerList(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Trailers> call, @NonNull Throwable t) {
                Log.v("Trailers Call", t.getMessage());
            }
        });


    }

    public void generateBackdropCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Backdrops> call = service.getBackdropsImage(movieId);
        call.enqueue(new Callback<Backdrops>() {
            @Override
            public void onResponse(@NonNull Call<Backdrops> call, @NonNull Response<Backdrops> response) {
                backdropsList = response.body().getBackdrops();
                Log.v("backdropsList Call", backdropsList.get(0).getFilePath());
                setBackboard(backdropsList.get(0).getFilePath());
            }

            @Override
            public void onFailure(@NonNull Call<Backdrops> call, @NonNull Throwable t) {
                Log.v("backdropsList Call", t.getMessage());
            }
        });


    }

    private void setBackboard(String filePath) {
        Glide.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w500" + filePath)
                .apply(new RequestOptions().override(Target.SIZE_ORIGINAL).dontTransform()).into(detailed_iv);
    }


    private void generateReviewsList(List<ResultReview> resultReviews) {
        if (resultReviews.size() < 1) {
            Log.v("Generate Review", "null");
            reviewLabel.setVisibility(View.INVISIBLE);
        } else {
            reviewLabel.setVisibility(View.VISIBLE);
            reviewsAdapter = new ReviewAdapter(this, resultReviews);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            reviews_rv.setLayoutManager(linearLayoutManager);
            reviews_rv.setAdapter(reviewsAdapter);
        }
    }

    private void generateTrailerList(List<ResultTrailer> resultTrailers) {
        if (resultTrailers.size() < 1) {
            Log.v("Generate Trailers", "null");
            trailerLabel.setVisibility(View.INVISIBLE);
        } else {
            trailerLabel.setVisibility(View.VISIBLE);
            trailerAdapter = new TrailerAdapter(this, resultTrailers);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            trailer_rv.setLayoutManager(linearLayoutManager);
            trailer_rv.setAdapter(trailerAdapter);
        }
    }

}
