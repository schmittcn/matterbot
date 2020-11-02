package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class DownsizedSmallImage {
    private String url;
    private String width;
    private String height;
    private String size;
}
