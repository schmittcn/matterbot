package org.matterbot.services.giphy;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

@Data
@Builder
public class GiphyMessageImpl implements MattermostMessage {
    private String giphyUrl;
    private String caption;

    @Override
    public String getMessage() {
        String NEWLINE = "\n";
        return caption + NEWLINE + giphyUrl;
    }
}
