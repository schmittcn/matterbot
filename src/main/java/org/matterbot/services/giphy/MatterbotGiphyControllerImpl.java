package org.matterbot.services.giphy;

import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MatterbotGiphyControllerImpl implements MatterbotGiphyController {
    private final URLQueryService giphyService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotGiphyControllerImpl(
            final MattermostServiceImpl mattermostServiceImpl,
            final @Qualifier("giphyServiceImpl") URLQueryService giphyService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.giphyService = giphyService;
    }

    @Override
    public ResponseEntity<String> postGiphyTrendingMessage() throws IOException {
        var giphyUrl = giphyService.getUrl(URLQueryService.Strategy.TRENDING);
        GiphyMessageImpl giphyMessageImpl = GiphyMessageImpl.builder()
                .caption("trending")
                .giphyUrl(giphyUrl)
                .build();
        return mattermostServiceImpl.sendMessage(giphyMessageImpl);
    }

    @Override
    public ResponseEntity<String> postGiphyRandomMessage() throws IOException {
        var giphyUrl = giphyService.getUrl(URLQueryService.Strategy.RANDOM);
        return mattermostServiceImpl.sendMessage(GiphyMessageImpl.builder()
                .caption("random")
                .giphyUrl(giphyUrl)
                .build());
    }

    @Override
    public ResponseEntity<String> postGiphySearchResult(final String query, final boolean random) throws IOException {
        var giphyUrl = "";
        if (random) {
            giphyUrl = giphyService.getUrl(URLQueryService.Strategy.SEARCH_RND, query);
        } else {
            giphyUrl = giphyService.getUrl(URLQueryService.Strategy.SEARCH, query);
        }
        return mattermostServiceImpl.sendMessage(GiphyMessageImpl.builder()
                .caption(query)
                .giphyUrl(giphyUrl)
                .build());
    }

    @Override
    public List<String> postGiphySearchResultList(String query) {
        return giphyService.getUrlList(query);
    }

    @Override
    public ResponseEntity<String> postGiphyImage(final String url, final String title) throws IOException {
        return mattermostServiceImpl.sendImage(url, title);
    }
}
