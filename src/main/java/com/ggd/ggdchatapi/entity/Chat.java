package com.ggd.ggdchatapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggd.ggdchatapi.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat extends BaseEntity {
    private String id;
    private String type;
    private String title;
    private Boolean active;
    private List<ContactChat> users = new ArrayList<>();
    private Timestamp lastMessage;

    @Id
    public String getId() {
        return id == null || id.equals("") ? UUID.randomUUID().toString() : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    //    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinTable(name = "chat_user",
////            joinColumns = @JoinColumn(name = "chat_id"),
////            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @JoinTable(name = "contact_message", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    public List<User> getUsers() {
//        return users;
//    }
    @OneToMany(
            mappedBy = "chat",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<ContactChat> getUsers() {
        return users;
    }

    public void setUsers(List<ContactChat> users) {
        this.users = users;
    }

    public Timestamp getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Timestamp lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void addUser(User user, String toId, Chat chat) {
        ContactChat cc = new ContactChat(user, toId, chat);
        users.add(cc);
        user.getChats().add(cc);
    }

}
