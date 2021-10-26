package org.matterbot.services.giphy;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

@Data
@Builder
public class GiphyMessageImpl implements MattermostMessage {
    private String giphyUrl;
    private String caption;

    private static final String NEWLINE = "\n";

    @Override
    public String getMessage() {
        return caption + NEWLINE + giphyUrl;
    }
}
