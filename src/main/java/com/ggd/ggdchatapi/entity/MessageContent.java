package com.ggd.ggdchatapi.entity;

import com.ggd.ggdchatapi.base.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MessageContent extends BaseEntity {
    private String id;
    private User sender;
    private Chat chat;
    private String content;
    private Integer flags;

    public MessageContent() {
    }

    public MessageContent(User sender, Chat chat, String content, Integer flags) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
        this.flags = flags;
    }

    @Id
    public String getId() {
        return id == null || id.equals("") ? UUID.randomUUID().toString() : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sender")
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "chat")
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
