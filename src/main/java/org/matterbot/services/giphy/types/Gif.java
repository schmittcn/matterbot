package org.matterbot.services.giphy.types;

import lombok.Data;

import java.util.Date;

@Data
public class Gif {
    private String type;
    private String id;
    private String slug;
    private String url;
    private String bitlyUrl;
    private String embedUrl;
    private String username;
    private String source;
    private String rating;
    private User user;
    private String sourceTld;
    private String sourcePostUrl;
    private Date updateDatetime;
    private Date createDatetime;
    private Date importDatetime;
    private Date trendingDatetime;
    private Images images;
    private String title;
}
