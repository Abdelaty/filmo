package com.example.filmo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.filmo.Adapters.MoviesAdapter;
import com.example.filmo.Model.movies.Movie;
import com.example.filmo.Model.movies.Result;
import com.example.filmo.Network.GetDataService;
import com.example.filmo.Network.RetrofitClientInstance;
import com.google.android.material.snackbar.Snackbar;

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
    public List<Result> nowPlayingMoviesList;
    @BindView(R.id.movies_main_rv)
    RecyclerView mainMovies_rv;
    MoviesAdapter moviesAdapter;
    @BindView(R.id.parent_view)
    RelativeLayout relativeLayout;
    private List<Result> topRatedMoviesList;
    private List<Result> upComingMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (isConnectedToInternet()) {
            generateNowPlayingCall();
        } else {
            Snackbar snackbar = Snackbar.make(relativeLayout, "Check the Internet Connection.", Snackbar.LENGTH_INDEFINITE).setAction("", view -> {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
            })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light));
            snackbar.show();

        }

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
                generateNowPlayingCall();
                break;

            case R.id.top_rated:
                Log.v("onOptionsItemSelected", "Top Rated Clicked");
                generateTopRatedCall();
                break;

            case R.id.up_coming:
                Log.v("onOptionsItemSelected", "Up Coming Clicked");
                generateUpComingCall();
                break;

            case R.id.fav:
                setTitle("Favourite");
                Log.v("onOptionsItemSelected", "Favourite Clicked");
                //     displayFav();


                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void generateNowPlayingList(List<Result> moviesList) {
        moviesAdapter = new MoviesAdapter(this, moviesList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mainMovies_rv.setLayoutManager(gridLayoutManager);
        mainMovies_rv.setAdapter(moviesAdapter);
    }

    private void displayFav(List<Result> moviesList) {
        moviesAdapter = new MoviesAdapter(this, moviesList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mainMovies_rv.setLayoutManager(gridLayoutManager);
        mainMovies_rv.setAdapter(moviesAdapter);
    }

    public void generateNowPlayingCall() {
        setTitle("Now Playing");
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
        setTitle("Top Rated");

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
        setTitle("Up Coming");
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

    //
//    class DatabaseTasks extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            if (!DatabaseClient.getInstance(context).getAppDatabase().moviesDao().getMovieWithId(moviesList.get(position).getId())) {
//                holder.fav_imageView.setImageResource(R.drawable.lace);
//                MoviesDbModel task = new MoviesDbModel();
//                task.setMovieId(moviesList.get(position).getId());
//                task.setMovieName(moviesList.get(position).getTitle());
//                task.setPosterUrl(moviesList.get(position).getPosterPath());
//                DatabaseClient.getInstance(context).getAppDatabase().moviesDao().insertAll(task);
//                Toast.makeText(context, "Item was Added", Toast.LENGTH_LONG).show();
//        }
    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
}

