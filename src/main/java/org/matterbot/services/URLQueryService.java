package org.matterbot.services;

import java.util.List;

public interface URLQueryService {
    enum Strategy {TRENDING, RANDOM, SEARCH, SEARCH_RND}

    String getUrl(Strategy strategy);

    String getUrl(Strategy strategy, String term);

    List<String> getUrlList(String term);
}
