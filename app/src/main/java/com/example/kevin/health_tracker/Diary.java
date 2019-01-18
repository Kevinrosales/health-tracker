package com.example.kevin.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kevin.health_tracker.Database.AppDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Diary extends AppCompatActivity {

    AppDatabase db;
    ListView exerciseEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_exercise);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "exercise").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        displayExercises();
    }
// I worked with Amy and Sooz with some help from nick Crain as well
    public void displayExercises() {
        List<Exercise> exercises = db.exerciseDao().getAll();
//        exerciseEntry = findViewById(R.id.exerciseEntrys);
        ArrayAdapter<Exercise> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, db.exerciseDao().getAll());
        exerciseEntry = findViewById(R.id.exerciseEntrys);
        exerciseEntry.setAdapter(arrayAdapter);
    }
// Darin Helped me figure out how to get it to actually time stamp instead of being another text entry
    public void recordExercise(View v){
        EditText titleInput = findViewById(R.id.addTitle);
        String title = titleInput.getText().toString();

        EditText quantityInput = findViewById(R.id.addQuantity);
        String quantity = quantityInput.getText().toString();

        EditText descriptionInput = findViewById(R.id.addDescription);
        String description = descriptionInput.getText().toString();

        Date date = new Date();
        String stampTime = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(stampTime);
        String timeStamp = dateFormat.format(date);

        Exercise recordedExercise = new Exercise(title, quantity, description, timeStamp);
        db.exerciseDao().insertAll(recordedExercise);
        startActivity(getIntent());
    }

}
