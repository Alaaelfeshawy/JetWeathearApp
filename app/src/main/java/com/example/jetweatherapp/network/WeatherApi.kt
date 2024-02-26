package com.example.jetweatherapp.network

import com.example.jetweatherapp.utils.Constants.API_KEY
import com.example.jetweatherapp.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("units") units: String? = "celsius",
        @Query("appid") appid: String = API_KEY // your api key
                          ): Weather
}