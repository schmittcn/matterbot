package org.matterbot.services.urbandictionary;

import java.io.IOException;
import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatterbotUrbanDictionaryControllerImpl {
    private final URLQueryService urbanDictionaryService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotUrbanDictionaryControllerImpl(
            final MattermostServiceImpl mattermostServiceImpl,
            final @Qualifier("urbanDictionaryServiceImpl") URLQueryService urbanDictionaryService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.urbanDictionaryService = urbanDictionaryService;
    }

    @GetMapping("/call/urbandict/search")
    public ResponseEntity<String> postUrbanSearchResult(final @RequestParam("query") String query) throws IOException {
        var urbanUrl = urbanDictionaryService.getUrl(URLQueryService.Strategy.SEARCH, query);
        UrbanDictionaryMessageImpl urbanMessage =
                UrbanDictionaryMessageImpl.builder()
                                          .caption(query)
                                          .description(urbanUrl)
                                          .build();
        return mattermostServiceImpl.sendMessage(urbanMessage);
    }
}
