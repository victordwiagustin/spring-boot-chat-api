package com.ggd.ggdchatapi.pojo;

public class ChatRecordsDetail {
    private String userId;
    private String msgId;
    private String message;

    public ChatRecordsDetail(String userId, String msgId, String message) {
        this.userId = userId;
        this.msgId = msgId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
