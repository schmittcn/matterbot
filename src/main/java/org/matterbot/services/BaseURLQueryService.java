package org.matterbot.services;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
public abstract class BaseURLQueryService implements URLQueryService {
    protected String queryCall(final Call<String> call, final String jsonPath) {
        try {
            Response<String> callResult = null;
            callResult = call.execute();
            if (callResult.isSuccessful()) {
                DocumentContext jsonContext = JsonPath.parse(callResult.body());
                return jsonContext.read(jsonPath);
            } else {
                log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "ERROR CALLING";
    }

    protected String queryCall(final Call<String> call) {
        try {
            Response<String> callResult = call.execute();
            if (callResult.isSuccessful()) {
                return callResult.body();
            } else {
                log.error("STATUS: {}, BODY: {}", callResult.code(), callResult.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "ERROR CALLING";
    }
}
