package com.example.filmo.Network;

import com.example.filmo.Model.Cast;
import com.example.filmo.Model.Movie;
import com.example.filmo.Model.Reviews;
import com.example.filmo.Model.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
//http://api.themoviedb.org/3/movie/now_playing?api_key=ceb888b71023afda704f84975d2642b5
//        " https://api.themoviedb.org/3/movie/"+movieId 3+"/videos?api_key="+apiKey;
//        String TRAILER_URL=" https://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key="+apiKey;
//        String CAST_URL="http://api.themoviedb.org/3/movie/"+movieId+"/casts?api_key="+apiKey;

public interface GetDataService {
    String API_KEY = "ceb888b71023afda704f84975d2642b5";

    @GET("now_playing?api_key=" + API_KEY)
    Call<Movie> getNowPlayingMovies();

    @GET("upcoming?api_key=" + API_KEY)
    Call<Movie> getUpComingMovies();

    @GET("top_rated?api_key=" + API_KEY)
    Call<Movie> getTopRatedMovies();

    @GET("@movieId/videos?api_key=" + API_KEY)
    Call<Trailers> getTrailer(
            @Query("movie_id") String movieId
    );

    @GET("@movieId/casts?api_key=" + API_KEY)
    Call<Cast> getCast(
            @Query("movie_id") String movieId
    );

    @GET("@movieId/reviews?api_key=" + API_KEY)
    Call<Reviews> getReviews(
            @Query("movie_id") String movieId
    );


}
