package com.ggd.ggdchatapi.pojo;

import java.util.List;

public class QueryFilter {
    private List<String> searchByList;
    private List<Object> valueList;
    private String searchBy;
    private Object value;
    private String orderBy = null;
    private String orderType = null;
    private Integer offset = null;
    private Integer limit = null;

    public List<String> getSearchByList() {
        return searchByList;
    }

    public void setSearchByList(List<String> searchByList) {
        this.searchByList = searchByList;
    }

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

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
}
