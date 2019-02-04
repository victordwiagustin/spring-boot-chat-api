package com.ggd.ggdchatapi.entity;

import com.ggd.ggdchatapi.base.BaseEntity;

import javax.persistence.*;

@Entity
public class ContactChat extends BaseEntity {
    private ContactChatId id;
    private User user;
    private String toId;
    private Chat chat;

    public ContactChat() {
    }

    public ContactChat(User user, String toId, Chat chat) {
        this.user = user;
        this.toId = toId;
        this.chat = chat;
        this.id = new ContactChatId(user.getId(), chat.getId());
    }

    @EmbeddedId
    public ContactChatId getId() {
        return id;
    }

    public void setId(ContactChatId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("chatId")
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
