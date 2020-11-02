package org.matterbot.services.giphy.types;

import lombok.Data;
import org.matterbot.services.giphy.types.Gif;
import org.matterbot.services.giphy.types.Meta;
import org.matterbot.services.giphy.types.Pagination;

import java.util.List;

@Data
public class SearchResponse {
    private List<Gif> data;
    private Pagination pagination;
    private Meta meta;
}
