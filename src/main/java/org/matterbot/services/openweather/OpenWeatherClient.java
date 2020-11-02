package org.matterbot.services.openweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface OpenWeatherClient {
    @GET("/data/2.5/weather?units=metric")
    @Headers("Content-Type: application/json")
    Call<String> getCurrentWeather(@Query("APPID") String api_key, @Query("q") String location);
}
