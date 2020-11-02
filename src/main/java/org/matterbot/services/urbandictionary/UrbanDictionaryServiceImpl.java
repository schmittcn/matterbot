package org.matterbot.services.urbandictionary;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

@Slf4j
@Service
public class UrbanDictionaryServiceImpl extends BaseURLQueryService {
    private final UrbanDictionaryClient urbanDictionaryClient;

    @Autowired
    public UrbanDictionaryServiceImpl(final UrbanDictionaryClient urbanDictionaryClient) {
        this.urbanDictionaryClient = urbanDictionaryClient;
    }

    @Override
    public String getUrl(final Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(final Strategy strategy, final String term) {
        Call<String> call;
        String jsonpath;

        if (strategy == Strategy.SEARCH) {
            call = urbanDictionaryClient.getTranslation(term);
            jsonpath = "$.list[0].definition";
        } else {
            return "WHAT DO YOU WANT, DUDE?";
        }
        return queryCall(call, jsonpath);
    }

    @Override
    public List<String> getUrlList(final String term) {
        return null;
    }
}
