package org.matterbot.mattermost.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MattermostHookData {
    private String username;
    private String icon_url;
    private String text;
}
