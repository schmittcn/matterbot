package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class FixedHeightSmallStillImage {
    private String url;
    private String width;
    private String height;
}
