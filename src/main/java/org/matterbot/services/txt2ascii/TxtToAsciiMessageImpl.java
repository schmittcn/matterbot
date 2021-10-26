package org.matterbot.services.txt2ascii;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

import static org.matterbot.services.Constants.NEWLINE;

@Data
@Builder
public class TxtToAsciiMessageImpl implements MattermostMessage {
    private String description;
    private String caption;

    @Override
    public String getMessage() {
        return caption + NEWLINE + description;
    }
}
