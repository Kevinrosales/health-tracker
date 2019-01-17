package com.example.kevin.health_tracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long exid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "quantity")
    public String quantity;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "timeStamp")
    public String timeStamp;

    @Ignore
    public Exercise(String title, String quantity, String description, String timeStamp) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public Exercise() {}

    public String toString() {
        return "Exercise: " + title + ", Quantity: " + quantity + ", Description: " + description + ", Time Stamp: " + timeStamp;
    }
}
