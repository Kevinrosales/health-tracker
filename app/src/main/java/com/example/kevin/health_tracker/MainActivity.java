package com.example.kevin.health_tracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.room.Room;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID="channelid";

    Button notification;
    TextView buttonCounter;
    public int counter=0;
    private int notificationId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCounter = findViewById(R.id.counter);
        notification = findViewById(R.id.notification);
        createNotificationChannel();


    }

    public void addToCounterOnClick(View v) {
        counter++;
        buttonCounter.setText(Integer.toString(counter));

    }

    public void takeMeAwayBreakMeAway(View v) {
        Intent StopwatchIntent = new Intent(this, stopwatch.class);
        startActivity(StopwatchIntent);
    }

    public void takeMeToSaveExercise(View v) {
        Intent SaveIntent = new Intent(this, Diary.class);
        startActivity(SaveIntent);
    }
// this idea came from (https://stackoverflow.com/questions/9406523/android-want-app-to-perform-tasks-every-second)
    public void sendNotification(View v) {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Health Tracker")
                                .setContentText("time to drink some water")
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("looks like its time to drink some water"))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId++, mBuilder.build());
            }
        }, 5000, 5000);
    }

// I got this code from the Android studio Docs (https://developer.android.com/training/notify-user/channels#java)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = (CHANNEL_ID);
//            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
