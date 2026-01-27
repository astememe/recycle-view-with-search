package com.astememe.minecraft_objects_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("items")
    Call<List<McItemAPIResponse>> getAllItems();

    @GET("items")
    Call<McItemAPIResponse> getItemByName(@Query("name") String nombre);
}
