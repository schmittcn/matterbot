package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class FixedWidthSmallStillImage {
    private String url;
    private String width;
    private String height;
}
