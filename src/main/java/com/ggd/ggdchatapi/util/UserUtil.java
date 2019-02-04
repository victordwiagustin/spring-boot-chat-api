package com.ggd.ggdchatapi.util;

import com.ggd.ggdchatapi.entity.User;

public class UserUtil {

    public static User trimUser(User user) {
        user.setChats(null);
        user.setApiKey(null);
        user.setCreatedDate(null);
        user.setUpdatedDate(null);

        return user;
    }
}
