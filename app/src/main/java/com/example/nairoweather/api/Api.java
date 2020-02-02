package com.example.nairoweather.api;

import com.example.nairoweather.models.GetNairoWeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    //getting current weather
    @GET
    Call<GetNairoWeatherInfo> getCurrentWeather(@Url String url);
}
