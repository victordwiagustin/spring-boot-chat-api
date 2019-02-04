package com.ggd.ggdchatapi.entity;

import com.ggd.ggdchatapi.base.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Deprecated
//TODO: Benerin struktur db (tampung 2 user ke 1 table)
//@Entity
public class DirectMessage extends BaseEntity {
    private String id;
    private User from;
    private User to;
    private String content;
    private Integer flags = 0;

    @Id
    public String getId() {
        return id == null || id.equals("") ? UUID.randomUUID().toString() : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_sender")
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_receive")
    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }
}
