package com.example.kevin.health_tracker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Insert
    void insertAll(Exercise... exercise);

    @Delete
    void delete(Exercise exercise);

}
