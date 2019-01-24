package com.example.kevin.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.gson.Gson;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kevin.health_tracker.Database.AppDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diary extends AppCompatActivity {

    AppDatabase db;
    ListView exerciseEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_exercise);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "exercise").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        displayExercises();
        getBackendData();
    }

// I worked with Amy and Sooz with some help from nick Crain as well
    public void displayExercises() {
        List<Exercise> exercises = db.exerciseDao().getAll();
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

        postBackendData(title, quantity, description);

        startActivity(getIntent());
    }

//////////////////////////////backend server things////////////////////////////////
// got this code from (https://developer.android.com/training/volley/simple#java)
    public void getBackendData() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://kevin-health-tracker.herokuapp.com/exercises";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        Gson gson =  new Gson();
                        Exercise[] exercises = gson.fromJson(response, Exercise[].class);
                        Log.d("Exercises", String.valueOf(exercises));
                        db.exerciseDao().insertAll(exercises);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response error", String.valueOf(error));
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void postBackendData(final String title, final String quantity, final String description) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://kevin-health-tracker.herokuapp.com/exercises";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // Response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error
                        Log.d("Error.Response", "It didn't work");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("title", title);
                params.put("quantity", quantity);
                params.put("description", description);

                return params;
            }
        };
        queue.add(postRequest);
    }


}