package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class Images {
    private FixedHeightImage fixedHeight;
    private FixedHeightStillImage fixedHeightStill;
    private FixedHeightDownsampledImage fixedHeightDownsampled;
    private FixedWidthImage fixedWidth;
    private FixedWidthStillImage fixedWidthStill;
    private FixedWidthDownsampledImage fixedWidthDownsampled;
    private FixedHeightSmallImage fixedHeightSmall;
    private FixedHeightSmallStillImage fixedHeightSmallStill;
    private FixedWidthSmallImage fixedWidthSmall;
    private FixedWidthSmallStillImage fixedWidthSmallStill;
    private DownsizedImage downsized;
    private DownsizedStillImage downsizedStill;
    private DownsizedLargeImage downsizedLarge;
    private DownsizedMediumImage downsizedMedium;
    private DownsizedSmallImage downsizedSmall;
    private OriginalImage original;
    private OriginalStillImage originalStill;
    private LoopingImage looping;
    private PreviewImage preview;
    private PreviewGifImage previewGif;
}
