package com.example.sep4androidapp;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GreenHouseAPI {
    @GET("sensor/{id}")
    Call<TemperatureResponse> getData(@Path("id") int id);
}
