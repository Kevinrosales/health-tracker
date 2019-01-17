package com.example.kevin.health_tracker.Database;

import com.example.kevin.health_tracker.Exercise;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
