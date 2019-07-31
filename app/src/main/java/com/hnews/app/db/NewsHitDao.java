package com.hnews.app.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hnews.app.models.Hit;

import java.util.List;

@Dao
public interface NewsHitDao {

    @Query("Select * from Hit")
    LiveData<List<Hit>> getNewsArticles();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNewsData(List<Hit> hitList);

}
