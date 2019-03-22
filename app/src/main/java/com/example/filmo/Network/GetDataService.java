package com.example.filmo.Network;

import com.example.filmo.Model.Movie;
import com.example.filmo.Model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    final String API_KEY = "ceb888b71023afda704f84975d2642b5";

    @GET("now_playing?api_key=" + API_KEY)
    Call<Movie> getNowPlayingMovies();

    @GET("upcoming?api_key=" + API_KEY)
    Call<Movie> getUpComingMovies();

    @GET("top_rated?api_key=" + API_KEY)
    Call<Movie> getTopRatedMovies();

}
