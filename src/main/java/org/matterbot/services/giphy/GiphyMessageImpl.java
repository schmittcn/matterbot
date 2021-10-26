package org.matterbot.services.giphy;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

import static org.matterbot.services.Constants.NEWLINE;

@Data
@Builder
public class GiphyMessageImpl implements MattermostMessage {
    private String giphyUrl;
    private String caption;

    @Override
    public String getMessage() {
        return caption + NEWLINE + giphyUrl;
    }
}
