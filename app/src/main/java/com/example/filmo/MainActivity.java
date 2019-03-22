package com.example.filmo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filmo.Model.Movie;
import com.example.filmo.Model.Result;
import com.example.filmo.Network.GetDataService;
import com.example.filmo.Network.RetrofitClientInstance;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //    String apiKey = BuildConfig.API_KEY;
    String apiKey = "ceb888b71023afda704f84975d2642b5";
    public String ImageBaseUrl = "http://image.tmdb.org/t/p/w185";
    String nowPlayingUrl = "http://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey;
    String upComingUrl = "http://api.themoviedb.org/3/movie/upcoming?api_key=" + apiKey;
    String topRatedUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey;
    @BindView(R.id.movies_main_rv)
    RecyclerView mainMovies_rv;
    MoviesAdapter moviesAdapter;
    public List<Result> nowPlayingMoviesList;
    private List<Result> topRatedMoviesList;
    private List<Result> upComingMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        generateNowPlayingCall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int clickedItem = item.getItemId();
        switch (clickedItem) {
            case R.id.now_playing:
                Log.v("onOptionsItemSelected", "Now Playing Clicked");
                setTitle("Now Playing");
                generateNowPlayingCall();

            case R.id.top_rated:
                Log.v("onOptionsItemSelected", "Top Rated Clicked");
                setTitle("Top Rated");
                generateTopRatedCall();

            case R.id.up_coming:
                Log.v("onOptionsItemSelected", "Up Coming Clicked");
                setTitle("Up Coming");
                generateUpComingCall();
            case R.id.fav:
                setTitle("Favourite");
                Log.v("onOptionsItemSelected", "Favourite Clicked");

        }
        return super.onOptionsItemSelected(item);
    }

    private void generateNowPlayingList(List<Result> moviesList) {
        moviesAdapter = new MoviesAdapter(this, moviesList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mainMovies_rv.setLayoutManager(gridLayoutManager);
        mainMovies_rv.setAdapter(moviesAdapter);
    }


    public void generateNowPlayingCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Movie> call = service.getNowPlayingMovies();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                nowPlayingMoviesList = response.body() != null ? response.body().getResults() : null;
                generateNowPlayingList(nowPlayingMoviesList);
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.v("NowPlaying Call", t.getMessage());
            }
        });


    }

    public void generateTopRatedCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Movie> call = service.getTopRatedMovies();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                topRatedMoviesList = response.body() != null ? response.body().getResults() : null;
                generateNowPlayingList(topRatedMoviesList);

            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.v("NowPlaying Call", t.getMessage());
            }
        });


    }

    public void generateUpComingCall() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Movie> call = service.getUpComingMovies();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                upComingMoviesList = response.body() != null ? response.body().getResults() : null;

                generateNowPlayingList(upComingMoviesList);

            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.v("NowPlaying Call", t.getMessage());
            }
        });


    }
}

