package com.example.sep4androidapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TemperatureRepository {
    private static TemperatureRepository instance;
    private final MutableLiveData<Temperature> receivedData;

    private TemperatureRepository()
    {
        receivedData = new MutableLiveData<>();
    }

    public static synchronized TemperatureRepository getInstance()
    {
        if(instance == null)
        {
            instance = new TemperatureRepository();
        }
        return instance;
    }

    public LiveData<Temperature> getData()
    {
        return receivedData;
    }

    public void getAttempt(int id)
    {
        GreenHouseAPI greenHouseAPI = ServiceGenerator.getGreenHouseAPI();

        Call<TemperatureResponse> call = greenHouseAPI.getData(id);

        call.enqueue(new Callback<TemperatureResponse>()
        {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<TemperatureResponse> call, Response<TemperatureResponse> response) {
                Log.i("Retrofit", "TAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaa");
                if(response.code() == 200)
                {
                    receivedData.setValue(response.body().getData());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<TemperatureResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.i("Retrofit", "The data could not reach you!" +t.getCause());
                }
                else{
                    Log.i("Retrofit", "The data could not reach you!NOT IO EXCEPTION");
                }

            }
        });
    }
}
