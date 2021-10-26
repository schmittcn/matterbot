package org.matterbot.mattermost.types;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MattermostAttachmentMessage {
    private String channel_id;
    private String username;
    private String icon_url;
    private String message;
    private List<Attachments> attachments;
}
