package com.example.kevin.health_tracker.Database;

import com.example.kevin.health_tracker.Database.ExerciseDao;
import com.example.kevin.health_tracker.Exercise;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
}
