package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class Meta {
    private String msg;
    private Integer status;
    private String responseId;
}
