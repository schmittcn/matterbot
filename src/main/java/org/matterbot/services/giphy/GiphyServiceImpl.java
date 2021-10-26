package org.matterbot.services.giphy;

import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.matterbot.services.giphy.types.DownsizedImage;
import org.matterbot.services.giphy.types.Gif;
import org.matterbot.services.giphy.types.Images;
import org.matterbot.services.giphy.types.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GiphyServiceImpl extends BaseURLQueryService {
    private final GiphyClient giphyClient;
    private static final int MAX_SEARCH_RESULT = 100;
    private final String apiKey;

    @Autowired
    public GiphyServiceImpl(final GiphyClient giphyClient,
                            final @NotNull @NotEmpty @NotBlank @Value("${giphy.client.apikey}") String apiKey) {
        this.giphyClient = giphyClient;
        this.apiKey = apiKey;
    }

    @Override
    public String getUrl(final Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(final Strategy strategy, final String term) {
        Call<String> call;
        String jsonpath;

        switch (strategy) {
            case RANDOM:
                call = giphyClient.getRandom(apiKey);
                jsonpath = "$.data.image_original_url";
                break;
            case TRENDING:
                call = giphyClient.getTrending(apiKey);
                jsonpath = "$.data[0].images.original.url";
                break;
            case SEARCH:
            case SEARCH_RND:
                var callSearch = giphyClient.search(apiKey, term, MAX_SEARCH_RESULT);
                String url = "";
                try {
                    var response = callSearch.execute();
                    if (response.isSuccessful()) {
                        SearchResponse body = response.body();
                        var indexToGet = 0;
                        if (strategy == Strategy.SEARCH_RND) {
                            indexToGet = getRandomNumberInRange(0, body.getData().size());
                        }
                        url = body.getData().get(indexToGet).getImages().getOriginal().getUrl();
                    } else {
                        log.error("STATUS: {}, BODY: {}", response.code(), response.errorBody().string());
                    }
                } catch (IOException ex) {
                    log.error(ex.toString());
                }
                return url;
            default:
                return "WHAT DO YOU WANT, DUDE?";
        }
        return queryCall(call, jsonpath);
    }

    public List<String> getUrlList(final String term) {
        var callSearch2 = giphyClient.search(apiKey, term, MAX_SEARCH_RESULT);
        try {
            var response = callSearch2.execute();
            if (response.isSuccessful()) {
                var body = response.body();
                assert body != null;
                var list = body.getData().stream().map(Gif::getImages).map(Images::getDownsized)
                        .map(DownsizedImage::getUrl).collect(Collectors.toList());
                if (list.size() > 5) {
                    return list.subList(0, 5);
                } else {
                    return list;
                }
            }
        } catch (IOException ex) {
            log.error(ex.toString());
        }
        return List.of();
    }

    private static int getRandomNumberInRange(final int min, final int max) {
        var r = new Random();
        return r.ints(min, max).findFirst().getAsInt();
    }
}
