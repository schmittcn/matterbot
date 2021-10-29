package org.matterbot.services.openweather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public interface MatterbotOpenWeatherController {

    @GetMapping("/call/openweather/location")
    ResponseEntity<String> getWeatherMessage(@RequestParam("query") String query) throws IOException;
}
