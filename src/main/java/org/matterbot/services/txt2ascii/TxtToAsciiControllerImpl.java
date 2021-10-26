package org.matterbot.services.txt2ascii;

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
public class TxtToAsciiControllerImpl {
    private final URLQueryService txtToAsciiService;
    private final MattermostServiceImpl mattermostServiceImpl;

    @Autowired
    private TxtToAsciiControllerImpl(final MattermostServiceImpl mattermostServiceImpl,
                                     @Qualifier("txtToAsciiServiceImpl") final URLQueryService txtToAsciiService) {
        this.mattermostServiceImpl = mattermostServiceImpl;
        this.txtToAsciiService = txtToAsciiService;
    }

    @GetMapping("/call/ascii/search")
    public ResponseEntity<String> postTxtToAsciiResult(@RequestParam("query") final String query) throws IOException {
        return mattermostServiceImpl.sendMessage(
                TxtToAsciiMessageImpl
                        .builder()
                        .caption(query)
                        .description("```" + txtToAsciiService.getUrl(URLQueryService.Strategy.SEARCH, query) + "```")
                        .build());
    }
}
