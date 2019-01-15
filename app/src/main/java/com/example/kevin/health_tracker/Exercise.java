package com.example.kevin.health_tracker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey
    public int exid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "timeStamp")
    public String timeStamp;
}
