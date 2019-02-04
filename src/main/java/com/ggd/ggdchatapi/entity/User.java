package com.ggd.ggdchatapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggd.ggdchatapi.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {

    private String id;
    private String email;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String apiKey;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ContactChat> chats = new ArrayList<>();

    public User() {
    }

    public User(String email, String name, String apiKey) {
        this.email = email;
        this.name = name;
        this.apiKey = apiKey;
    }

    public User(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.DETACH,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    public List<ContactChat> getChats() {
        return chats;
    }

    public void setChats(List<ContactChat> chats) {
        this.chats = chats;
    }

//    public void addChat(Chat chat) {
//        ContactChat cc = new ContactChat(user, toId, this);
//        users.add(cc);
//        user.getChats().add(cc);
//    }

}
