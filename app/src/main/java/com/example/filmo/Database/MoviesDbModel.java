package com.example.filmo.Database;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoviesDbModel implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
//
//    public String getMovieName() {
//        return movieName;
//    }
//
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    public String getPosterUrl() {
//        return posterUrl;
//    }
//
//    public void setPosterUrl(String posterUrl) {
//        this.posterUrl = posterUrl;
//    }
//
////    @ColumnInfo(name = "movie_name")
////    private String movieName;
////
////    @ColumnInfo(name = "poster_url")
////    private String posterUrl;


}
