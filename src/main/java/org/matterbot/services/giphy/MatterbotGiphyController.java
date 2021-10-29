package org.matterbot.services.giphy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public interface MatterbotGiphyController {

    @GetMapping("/call/giphy/trending")
    ResponseEntity<String> postGiphyTrendingMessage() throws IOException;

    @GetMapping("/call/giphy/random")
    ResponseEntity<String> postGiphyRandomMessage() throws IOException;

    @GetMapping("/call/giphy/search")
    ResponseEntity<String> postGiphySearchResult(
            @RequestParam("query") String query,
            @RequestParam(value = "random", required = false, defaultValue = "false") boolean random)
            throws IOException;


    @GetMapping(value = "/call/giphy/search2", produces = "application/json")
    List<String> postGiphySearchResultList(@RequestParam("query") String query);

    @GetMapping("/call/giphy/img")
    ResponseEntity<String> postGiphyImage(@RequestParam("url") String url, @RequestParam("title") String title)
            throws IOException;
}
