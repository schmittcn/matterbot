package org.matterbot.services.giphy;

import org.matterbot.services.giphy.types.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GiphyClient {
    @GET("/v1/gifs/trending")
    @Headers("Content-Type: application/json")
    Call<String> getTrending(
            @Query("api_key") String api_key,
            @Query("limit") int limit,
            @Query("rating") String rating
    );

    @GET("/v1/gifs/trending")
    @Headers("Content-Type: application/json")
    Call<String> getTrending(
            @Query("api_key") String api_key
    );

    @GET("/v1/gifs/random")
    @Headers("Content-Type: application/json")
    Call<String> getRandom(
            @Query("api_key") String api_key,
            @Query("tag") String tag,
            @Query("rating") String rating
    );

    @GET("/v1/gifs/random")
    @Headers("Content-Type: application/json")
    Call<String> getRandom(
            @Query("api_key") String api_key
    );

    @GET("/v1/gifs/search")
    @Headers("Content-Type: application/json")
    Call<String> search(
            @Query("api_key") String api_key,
            @Query("q") String query,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("rating") String rating,
            @Query("lang") String lang, //2-letter ISO 639-1 language code
            @Query("fmt") String fmt // format defaults to json
    );

    @GET("/v1/gifs/search")
    @Headers("Content-Type: application/json")
    Call<SearchResponse> search(
            @Query("api_key") String api_key,
            @Query("q") String query,
            @Query("limit") int limit
    );
}
