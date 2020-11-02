package org.matterbot.services.giphy;

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
import java.util.List;

@RestController
public class MatterbotGiphyControllerImpl {
    private final URLQueryService giphyService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private MatterbotGiphyControllerImpl(
            final MattermostServiceImpl mattermostServiceImpl,
            final @Qualifier("giphyServiceImpl") URLQueryService giphyService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.giphyService = giphyService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/trending"})
    public ResponseEntity<String> postGiphyTrendingMessage() throws IOException {
        String giphyUrl = giphyService.getUrl(URLQueryService.Strategy.TRENDING);
        GiphyMessageImpl giphyMessageImpl = GiphyMessageImpl.builder()
                .caption("trending")
                .giphyUrl(giphyUrl)
                .build();
        return mattermostServiceImpl.sendMessage(giphyMessageImpl);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/random"})
    public ResponseEntity<String> postGiphyRandomMessage() throws IOException {
        String giphyUrl = giphyService.getUrl(URLQueryService.Strategy.RANDOM);
        return mattermostServiceImpl.sendMessage(GiphyMessageImpl.builder()
                .caption("random")
                .giphyUrl(giphyUrl)
                .build());
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/search"})
    public ResponseEntity<String> postGiphySearchResult(
            final @RequestParam("query") String query,
            final @RequestParam(value = "random", required = false, defaultValue = "false") boolean random)
            throws IOException {
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

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/search2"}, produces = "application/json")
    public List<String> postGiphySearchResultList(final @RequestParam("query") String query) {
        return giphyService.getUrlList(query);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/img"})
    public ResponseEntity<String> postGiphyImage(final @RequestParam("url") String url,
                                                 final @RequestParam("title") String title) throws IOException {
        return mattermostServiceImpl.sendImage(url, title);
    }
}
