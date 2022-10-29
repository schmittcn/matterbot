package org.matterbot.services.giphy.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private DownsizedImage downsized;
    private OriginalImage original;
}
