package com.hnews.app.network;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hnews.app.models.HNModel;
import com.hnews.app.models.Hit;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HNApiClient {
    private static final String NEWS_API_URL = "http://hn.algolia.com";
    private static final Object LOCK = new Object();
    private static IHNewsService serviceInterface;
    private static HNApiClient clientInstance;


    private HNApiClient(){}

    public static HNApiClient getInstance(Context context){
        if (clientInstance == null || serviceInterface == null){
            synchronized (LOCK){
                Cache cache = new Cache(context.getApplicationContext().getCacheDir(), 5 * 1024 * 1024);

                // Used for cache connection
                Interceptor networkInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // set max-age and max-stale properties for cache header
                        CacheControl cacheControl = new CacheControl.Builder()
                                .maxAge(1, TimeUnit.HOURS)
                                .maxStale(3, TimeUnit.DAYS)
                                .build();
                        return chain.proceed(chain.request())
                                .newBuilder()
                                //.removeHeader("Pragma")
                                //.header("Cache-Control", cacheControl.toString())
                                .build();
                    }
                };

                // For logging
                HttpLoggingInterceptor loggingInterceptor =
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


                // Building OkHttp client
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .addNetworkInterceptor(networkInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .build();

                // Configure GSON
                Gson gson = new GsonBuilder()
                        .create();

                // Retrofit Builder
                Retrofit.Builder builder =
                        new Retrofit
                                .Builder()
                                .baseUrl(NEWS_API_URL)
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create(gson));
                // Set NewsApi instance
                serviceInterface = builder.build().create(IHNewsService.class);
                clientInstance= new HNApiClient();
            }
        }
        return clientInstance;
    }

    public LiveData<List<Hit>> getNewsList(String query){
        final MutableLiveData<List<Hit>> hnModelLiveData = new MutableLiveData<>();
        Call<HNModel> networkCall = serviceInterface.getNews(query);

        networkCall.enqueue(new Callback<HNModel>() {
            @Override
            public void onResponse(Call<HNModel> call, retrofit2.Response<HNModel> response) {
                Log.d(getClass().getName(),"Data "+response.body());

                if (response.body() != null) {
                    List<Hit> hitList = response.body().getHits();
                    hnModelLiveData.setValue(hitList);
                }
            }

            @Override
            public void onFailure(Call<HNModel> call, Throwable t) {

            }
        });
        return hnModelLiveData;
    }
}
