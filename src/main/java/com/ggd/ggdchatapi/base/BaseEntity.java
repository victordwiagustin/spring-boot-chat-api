package com.ggd.ggdchatapi.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp createdDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp updatedDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }
}
