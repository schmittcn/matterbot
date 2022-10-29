package org.matterbot.mattermost.types;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MattermostAttachmentMessage {
    private String username;
    private String icon_url;
    private String message;
    private List<Attachments> attachments;
}
