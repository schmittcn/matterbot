package org.matterbot.services.openweather;

import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MatterbotOpenWeatherControllerImpl implements MatterbotOpenWeatherController {
    private final URLQueryService openWeatherService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotOpenWeatherControllerImpl(final MattermostServiceImpl mattermostServiceImpl,
                                               final @Qualifier("openWeatherServiceImpl")
                                                       URLQueryService openWeatherService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.openWeatherService = openWeatherService;
    }

    @Override
    public ResponseEntity<String> getWeatherMessage(String query) throws IOException {
        var json = openWeatherService.getUrl(null, query);
        log.info("WEATHER: " + json);
        var jsonContext = JsonPath.parse(json);

        OpenWeatherMessageImpl giphyMessage =
                OpenWeatherMessageImpl.builder()
                        .caption("Weather for '" + query + "'")
                        .temp(jsonContext.read("$.main.temp").toString())
                        .tempMin(jsonContext.read("$.main.temp_min").toString())
                        .tempMax(jsonContext.read("$.main.temp_max").toString())
                        .pressure(jsonContext.read("$.main.pressure").toString())
                        .humidity(jsonContext.read("$.main.humidity").toString())
                        .wind(jsonContext.read("$.wind.speed").toString())
                        .weather(jsonContext.read("$.weather[0].description").toString())
                        .icon(jsonContext.read("$.weather[0].icon").toString())
                        .build();

        return mattermostServiceImpl.sendMessage(giphyMessage);
    }
}
