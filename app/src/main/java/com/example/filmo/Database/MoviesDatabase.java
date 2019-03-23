package com.example.filmo.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MoviesDbModel.class}, version = 1, exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
