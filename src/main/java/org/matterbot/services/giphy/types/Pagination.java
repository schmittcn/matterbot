package org.matterbot.services.giphy.types;

import lombok.Data;

@Data
public class Pagination {
    private Integer offset;
    private Integer totalCount;
    private Integer count;
}
