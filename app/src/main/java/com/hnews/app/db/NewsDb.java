package com.hnews.app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hnews.app.models.Hit;

@Database(entities = {Hit.class},
        version = 1,
        exportSchema = false)
public abstract class NewsDb extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "news_app";
    private static NewsDb sInstance;

    public static NewsDb getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        NewsDb.class,
                        DATABASE_NAME).build();
            }
        }
        return sInstance;
    }

    public abstract NewsHitDao newsHitDao();

}
