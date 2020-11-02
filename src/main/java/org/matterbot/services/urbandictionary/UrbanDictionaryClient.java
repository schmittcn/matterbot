package org.matterbot.services.urbandictionary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface UrbanDictionaryClient {
    @GET("/v0/define")
    @Headers("Content-Type: application/json")
    Call<String> getTranslation(@Query("term") String term);
}
