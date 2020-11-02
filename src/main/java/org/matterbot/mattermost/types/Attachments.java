package org.matterbot.mattermost.types;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attachments {
    private String title;
    private String title_link;

    private String pretext;
    private String text;

    private String image_url;
}
