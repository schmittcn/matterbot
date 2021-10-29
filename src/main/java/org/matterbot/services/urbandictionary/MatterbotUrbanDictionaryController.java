package org.matterbot.services.urbandictionary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MatterbotUrbanDictionaryController {

    @GetMapping("/call/urbandict/search")
    ResponseEntity<String> postUrbanSearchResult(@RequestParam("query") String query) throws IOException;
}
