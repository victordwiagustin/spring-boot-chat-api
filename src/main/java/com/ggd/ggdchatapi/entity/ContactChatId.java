package com.ggd.ggdchatapi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContactChatId implements Serializable{
    private String userId;
    private String chatId;

    public ContactChatId() {
    }

    public ContactChatId(String userId, String chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "chat_id")
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
