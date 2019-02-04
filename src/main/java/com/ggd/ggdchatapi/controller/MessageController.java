package com.ggd.ggdchatapi.controller;

import com.ggd.ggdchatapi.base.BaseController;
import com.ggd.ggdchatapi.entity.DirectMessage;
import com.ggd.ggdchatapi.pojo.ChatRecords;
import com.ggd.ggdchatapi.pojo.GetMessage;
import com.ggd.ggdchatapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @PostMapping("send-message")
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody Map<String, Object> reqBody) {
        try {
            String type = reqBody.get("type").toString();
            String from = reqBody.get("from").toString();
            String to = reqBody.get("to").toString();
            String content = reqBody.get("content").toString();

            if (type.trim().toLowerCase().equals("private")) {
                messageService.savePrivateMessage(from, to, content);
            }

            return responseOk();
        } catch (Exception e) {
            e.printStackTrace();
            return responseError();
        }
    }

    @PostMapping("get-message")
    public ResponseEntity<Map<String, Object>> getMessage(@RequestBody Map<String, Object> reqBody) {
        try {
            GetMessage getMessage = objectMapper.convertValue(reqBody, GetMessage.class);
            List<ChatRecords> chatRecordsList = messageService.getMessage(getMessage);

            return responseOk(chatRecordsList);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError();
        }
    }

}
