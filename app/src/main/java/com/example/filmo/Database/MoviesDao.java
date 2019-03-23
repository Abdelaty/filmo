package com.example.filmo.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM moviesdbmodel")
    List<MoviesDbModel> getAll();

    @Insert
    void insertAll(MoviesDbModel users);

    @Delete
    void delete(MoviesDbModel user);

    @Query("SELECT * FROM moviesdbmodel WHERE movie_id = :number")
    public boolean getMovieWithId(int number);
}
