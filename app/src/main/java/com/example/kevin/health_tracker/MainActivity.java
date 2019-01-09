package com.example.kevin.health_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID="channelid";

    Button notification;
    TextView buttonCounter;
    public int counter=0;
    private int notificationId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCounter = findViewById(R.id.counter);
        notification = findViewById(R.id.notification);
    }
    public void addToCounterOnClick(View v) {
        counter++;
        buttonCounter.setText(Integer.toString(counter));

    }

    public void takeMeAwayBreakMeAway(View v) {
        Intent StopwatchIntent = new Intent(this, stopwatch.class);
        startActivity(StopwatchIntent);
    }

    public void sendNotification(View v) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Health Tracker")
                .setContentText("time to drink some water")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("ITS TIME TO DRINK WaTeR"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId++, mBuilder.build());
    }
}
