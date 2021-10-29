package org.matterbot.services.urbandictionary;

import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Component
public class MatterbotUrbanDictionaryControllerImpl implements MatterbotUrbanDictionaryController {
    private final URLQueryService urbanDictionaryService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotUrbanDictionaryControllerImpl(
            final MattermostServiceImpl mattermostServiceImpl,
            final @Qualifier("urbanDictionaryServiceImpl") URLQueryService urbanDictionaryService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.urbanDictionaryService = urbanDictionaryService;
    }

    @Override
    public ResponseEntity<String> postUrbanSearchResult(final String query) throws IOException {
        var urbanUrl = urbanDictionaryService.getUrl(URLQueryService.Strategy.SEARCH, query);
        UrbanDictionaryMessageImpl urbanMessage =
                UrbanDictionaryMessageImpl.builder()
                        .caption(query)
                        .description(urbanUrl)
                        .build();
        return mattermostServiceImpl.sendMessage(urbanMessage);
    }
}
