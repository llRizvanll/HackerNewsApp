package com.hnews.app.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hnews.app.models.Hit;
import com.hnews.app.network.AppExecutors;
import com.hnews.app.network.HNApiClient;

import java.util.List;

public class NewsRepository {

    private static final Object LOCK = new Object();
    private static NewsRepository sInstance;
    private final AppExecutors mExecutor;
    private final HNApiClient apiClient;
    private final NewsHitDao newsHitDao;
    private final MutableLiveData<List<Hit>> newsListLiveData;


    private NewsRepository(Context context){
        apiClient = HNApiClient.getInstance(context);
        mExecutor = AppExecutors.getInstance();
        newsListLiveData = new MutableLiveData<>();
        newsHitDao = NewsDb.getInstance(context).newsHitDao();

        //update news data
        newsListLiveData.observeForever(new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hitList) {
                if (hitList!=null){
                    mExecutor.getDiskIO().execute(() -> { newsHitDao.insertNewsData(hitList);});
                }
            }
        });

    }

    public synchronized static NewsRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NewsRepository(context);
            }
        }
        return sInstance;
    }

    public LiveData<List<Hit>> getNewsList(String query){
        final LiveData<List<Hit>> networkNewsData = apiClient.getNewsList(query);

        networkNewsData.observeForever(new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hitList) {
                if (hitList!=null){
                    newsListLiveData.setValue(hitList);
                    networkNewsData.removeObserver(this);
                }
            }
        });

        return newsHitDao.getNewsArticles();
    }
}
