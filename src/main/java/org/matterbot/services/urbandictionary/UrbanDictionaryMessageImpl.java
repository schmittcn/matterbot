package org.matterbot.services.urbandictionary;

import lombok.Builder;
import lombok.Data;
import org.matterbot.mattermost.MattermostMessage;

@Data
@Builder
public class UrbanDictionaryMessageImpl implements MattermostMessage {
    private String description;
    private String caption;
    private final String NEWLINE = "\n";

    @Override
    public String getMessage() {
        return caption + NEWLINE + description;
    }
}
