package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class FixedHeightStillImage {
    private String url;
    private String width;
    private String height;
}
