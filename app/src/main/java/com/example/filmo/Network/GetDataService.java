package com.example.filmo.Network;

import com.example.filmo.Model.backdrops.Backdrops;
import com.example.filmo.Model.cast.Cast;
import com.example.filmo.Model.movies.Movie;
import com.example.filmo.Model.reviews.Reviews;
import com.example.filmo.Model.trailer.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("{id}/videos?api_key=" + API_KEY)
    Call<Trailers> getTrailer(
            @Path("id") int movieId
    );

    @GET("{id}/casts?api_key=" + API_KEY)
    Call<Cast> getCast(
            @Path("id") int movieId
    );

    @GET("{id}/reviews?api_key=" + API_KEY)
    Call<Reviews> getReviews(
            @Path("id") int movieId
    );

    @GET("{id}/images?api_key=" + API_KEY)
    Call<Backdrops> getBackdropsImage(
            @Path("id") int movieId
    );

}
