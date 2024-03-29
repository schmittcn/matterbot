package org.matterbot.services.txt2ascii;

import org.matterbot.mattermost.MattermostServiceImpl;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TxtToAsciiControllerImpl implements TxtToAsciiController {
    private final URLQueryService txtToAsciiService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private TxtToAsciiControllerImpl(final MattermostServiceImpl mattermostServiceImpl,
                                     @Qualifier("txtToAsciiServiceImpl") final URLQueryService txtToAsciiService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.txtToAsciiService = txtToAsciiService;
    }

    @Override
    public ResponseEntity<String> postTxtToAsciiResult(final String query) throws IOException {
        return mattermostServiceImpl.sendMessage(
                TxtToAsciiMessageImpl
                        .builder()
                        .caption(query)
                        .description("```" + txtToAsciiService.getUrl(URLQueryService.Strategy.SEARCH, query) + "```")
                        .build());
    }
}
