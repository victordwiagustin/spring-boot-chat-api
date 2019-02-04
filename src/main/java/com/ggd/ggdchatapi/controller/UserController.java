package com.ggd.ggdchatapi.controller;

import com.ggd.ggdchatapi.base.BaseController;
import com.ggd.ggdchatapi.entity.User;
import com.ggd.ggdchatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody Map<String, Object> reqBody) {
        User user = objectMapper.convertValue(reqBody, User.class);
        userService.save(user);
        return responseOk();
    }

    @PostMapping("update")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody Map<String, Object> reqBody) {
        User user = objectMapper.convertValue(reqBody, User.class);
        userService.update(user);
        return responseOk();
    }

}
