package com.ggd.ggdchatapi.pojo;

import java.util.List;

public class GetMessage {
    private Integer offset;
    private Integer limit;
    private String userId; //request user
    private List<Narrow> narrow;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Narrow> getNarrow() {
        return narrow;
    }

    public void setNarrow(List<Narrow> narrow) {
        this.narrow = narrow;
    }
}

