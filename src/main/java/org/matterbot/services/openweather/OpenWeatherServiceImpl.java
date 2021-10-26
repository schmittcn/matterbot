package org.matterbot.services.openweather;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.services.BaseURLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OpenWeatherServiceImpl extends BaseURLQueryService {
    private final OpenWeatherClient openWeatherClient;
    @NotNull
    @NotEmpty
    @NotBlank
    @Value("${openweather.client.apikey}")
    private String apiKey;

    @Autowired
    public OpenWeatherServiceImpl(final OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    @Override
    public String getUrl(final Strategy strategy) {
        return getUrl(strategy, "");
    }

    @Override
    public String getUrl(final Strategy strategy, final String term) {
        return queryCall(openWeatherClient.getCurrentWeather(apiKey, term));
    }

    @Override
    public List<String> getUrlList(final String term) {
        return null;
    }
}
