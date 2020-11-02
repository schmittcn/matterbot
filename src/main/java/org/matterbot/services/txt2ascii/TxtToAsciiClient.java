package org.matterbot.services.txt2ascii;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TxtToAsciiClient {
    @GET("/make")
    @Headers("Content-Type: application/json")
    Call<String> getAsciiArtWithFont(@Query("text") String text,
                                     @Query("font") String font);

    @GET("/make")
    @Headers("Content-Type: application/json")
    Call<String> getAsciiArt(@Query("text") String text);
}
