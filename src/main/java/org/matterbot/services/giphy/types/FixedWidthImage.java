package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class FixedWidthImage {
    private String url;
    private String width;
    private String height;
    private String size;
    private String mp4;
    private String mp4Size;
    private String webp;
    private String webp_size;
}
