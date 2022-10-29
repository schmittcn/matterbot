package org.matterbot.mattermost.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachments {
    private String title;
    private String title_link;
    private String text;
    private String image_url;
}
