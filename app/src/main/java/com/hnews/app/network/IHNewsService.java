package com.hnews.app.network;

import com.hnews.app.models.HNModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IHNewsService {

    @GET("/search")
    Call<HNModel> getNews(
            @Query("query") String category
    );
}
