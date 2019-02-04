package com.ggd.ggdchatapi;

import com.ggd.ggdchatapi.controller.UserController;
import com.ggd.ggdchatapi.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GgdChatApiApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private MessageService messageService;


    @Test
    public void contextLoads() throws Exception {
//        Map<String, Object> reqBody = new HashMap<>();
//
//        reqBody.put("id", "user1");
//        reqBody.put("email", "user1@test.test");
//        reqBody.put("name", "user one");
//        reqBody.put("apiKey", "12345");
//
//        userController.addUser(reqBody);
//
//        reqBody.put("id", "user2");
//        reqBody.put("email", "user2@test.test");
//        reqBody.put("name", "user two");
//        reqBody.put("apiKey", "12345");
//
//        userController.addUser(reqBody);

        messageService.savePrivateMessage("user1", "user2", "test");
    }


}

