package com.ggd.ggdchatapi.service;

import com.ggd.ggdchatapi.pojo.ChatRecords;
import com.ggd.ggdchatapi.pojo.GetMessage;

import java.util.List;

public interface MessageService {

    List<ChatRecords> getMessage(GetMessage getMessage);

    void savePrivateMessage(String from, String to, String content);

}
