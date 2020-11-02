package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class PreviewGifImage {
    private String url;
    private String width;
    private String height;
    private String size;
}
