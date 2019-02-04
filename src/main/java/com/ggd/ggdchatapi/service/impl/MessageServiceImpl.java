package com.ggd.ggdchatapi.service.impl;

import antlr.collections.impl.IntRange;
import com.ggd.ggdchatapi.dao.GenericDao;
import com.ggd.ggdchatapi.entity.Chat;
import com.ggd.ggdchatapi.entity.ContactChat;
import com.ggd.ggdchatapi.entity.MessageContent;
import com.ggd.ggdchatapi.entity.User;
import com.ggd.ggdchatapi.pojo.ChatRecords;
import com.ggd.ggdchatapi.pojo.ChatRecordsDetail;
import com.ggd.ggdchatapi.pojo.GetMessage;
import com.ggd.ggdchatapi.pojo.Narrow;
import com.ggd.ggdchatapi.service.MessageService;
import com.ggd.ggdchatapi.util.DateUtil;
import com.ggd.ggdchatapi.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class MessageServiceImpl implements MessageService {

    private GenericDao<MessageContent> messageContentRepo;
    private GenericDao<Chat> chatRepo;
    private GenericDao<ContactChat> contactChatRepo;

    @Autowired
    public MessageServiceImpl(
            GenericDao<MessageContent> messageContentRepo,
            GenericDao<Chat> chatRepo,
            @Qualifier("contactChatRepo") GenericDao<ContactChat> contactChatRepo
    ) {
        this.messageContentRepo = messageContentRepo;
        this.chatRepo = chatRepo;
        this.contactChatRepo = contactChatRepo;

        this.messageContentRepo.setClazz(MessageContent.class);
        this.chatRepo.setClazz(Chat.class);
        this.contactChatRepo.setClazz(ContactChat.class);
    }

    public List<ChatRecords> getMessage(GetMessage getMessage) {
        List<ChatRecords> chatRecordsList = new ArrayList<>();
        for (Narrow narrow : getMessage.getNarrow()) {
//            Chat chat = chatRepo.findOne(getMessage.getUserId(), narrow.getOperator());
            List<String> searchBy = Arrays.asList("user.id", "toId");
            List<Object> value = Arrays.asList(getMessage.getUserId(), narrow.getOperator());
            ContactChat contactChat = contactChatRepo.findOne(searchBy, value);
            Chat chat = chatRepo.findOne(contactChat.getChat().getId());

            List<MessageContent> messageContents = messageContentRepo.findAll(
                    "chat.id",
                    contactChat.getChat().getId(),
                    "createdDate",
                    "ASC",
                    getMessage.getOffset(),
                    getMessage.getLimit()
            );

            List<ChatRecordsDetail> chatRecordsDetails = new ArrayList<>();
            for (MessageContent msgCnt : messageContents) {
                ChatRecordsDetail detail = new ChatRecordsDetail(msgCnt.getSender().getId(), msgCnt.getId(), msgCnt.getContent());
                chatRecordsDetails.add(detail);
            }

            ChatRecords chatRecords = new ChatRecords();
            chatRecords.setUsers(new ArrayList<>());
            chat.getUsers().forEach(cc -> {
                User user = UserUtil.trimUser(cc.getUser());
                chatRecords.getUsers().add(user);
            });
//            chatRecords.setUsers(chat.getUsers());
            chatRecords.setGroupId(null);
            chatRecords.setContent(chatRecordsDetails);

            chatRecordsList.add(chatRecords);
        }


        return chatRecordsList;
    }

    @Transactional
    public void savePrivateMessage(String from, String to, String content) {

        try {
            List<String> searchBy = Arrays.asList("user.id", "toId");
            List<Object> value = Arrays.asList(from, to);

            User user1 = new User(from);
            User user2 = new User(to);

            ContactChat contactChat;
            try {
                contactChat = contactChatRepo.findOne(searchBy, value);
            } catch (NoResultException e) {
                contactChat = null;
            }

            Chat chat;
            if (contactChat == null) {
                chat = new Chat();
                chat.setActive(true);
                chat.setLastMessage(DateUtil.now());
                chat.setTitle(null);
                chat.setType("private");
                chat.setId(chat.getId());

                chat.addUser(user1, user2.getId(), chat);
                chat.addUser(user2, user1.getId(), chat);

                chatRepo.update(chat);
            } else {
                chat = contactChat.getChat();
                chat.setLastMessage(DateUtil.now());
                chatRepo.update(chat);
            }

            MessageContent messageContent = new MessageContent(user1, chat, content, 0);
            messageContentRepo.save(messageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
