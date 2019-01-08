package com.example.kevin.health_tracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView buttonCounter;
    public int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCounter = findViewById(R.id.counter);
    }
    public void addToCounterOnClick(View v){
        counter++;
        buttonCounter.setText(Integer.toString(counter));

    }
}
