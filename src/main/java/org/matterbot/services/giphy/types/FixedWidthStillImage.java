package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class FixedWidthStillImage {
    private String url;
    private String width;
    private String height;
}
