package org.matterbot.services.openweather;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class MatterbotOpenWeatherControllerImpl {
    private final URLQueryService openWeatherService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotOpenWeatherControllerImpl(final MattermostServiceImpl mattermostServiceImpl,
                                               final @Qualifier("openWeatherServiceImpl")
                                                       URLQueryService openWeatherService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.openWeatherService = openWeatherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/openweather/location"})
    public ResponseEntity<String> getWeatherMessage(final @RequestParam("query") String query) throws IOException {
        String json = openWeatherService.getUrl(null, query);

        log.info("WEATHER: " + json);

        DocumentContext jsonContext = JsonPath.parse(json);

        OpenWeatherMessageImpl giphyMessage = OpenWeatherMessageImpl.builder()
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
