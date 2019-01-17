package com.example.kevin.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Diary extends AppCompatActivity {

    AppDatabase db;
    ListView DiaryNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_exercise);
        displayExercises();
    }

    public void displayExercises() {
        List<Exercise> exercises = db.exerciseDao().getAll();
        DiaryNotes = findViewById(R.id.exerciseEntrys);
        ArrayAdapter<Exercise> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        DiaryNotes.setAdapter(arrayAdapter);
    }

    public void recordExercise(View v){
         EditText titleInput = findViewById(R.id.addTitle);
        String title = titleInput.getText().toString();

         EditText quantityInput = findViewById(R.id.addQuantity);
        String quantity = quantityInput.getText().toString();

         EditText descriptionInput = findViewById(R.id.addDescription);
        String description = descriptionInput.getText().toString();

        EditText timeInput = findViewById(R.id.addTimeStamp);
        String timeStamp = timeInput.getText().toString();

        Exercise recordedExercise = new Exercise(title, quantity, description, timeStamp);
        db.exerciseDao().insertAll(recordedExercise);
        finish();
        startActivity(getIntent());
    }

}
