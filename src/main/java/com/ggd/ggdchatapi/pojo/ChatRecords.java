package com.ggd.ggdchatapi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggd.ggdchatapi.entity.User;

import java.util.List;

public class ChatRecords {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String groupId;

    private List<ChatRecordsDetail> content;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<ChatRecordsDetail> getContent() {
        return content;
    }

    public void setContent(List<ChatRecordsDetail> content) {
        this.content = content;
    }
}
