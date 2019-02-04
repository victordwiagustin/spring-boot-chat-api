package com.ggd.ggdchatapi.service.impl;

import com.ggd.ggdchatapi.dao.GenericDao;
import com.ggd.ggdchatapi.entity.User;
import com.ggd.ggdchatapi.service.UserService;
import com.ggd.ggdchatapi.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private GenericDao<User> userRepo;

    @Autowired
    public UserServiceImpl(GenericDao<User> genericDao) {
        userRepo = genericDao;
        userRepo.setClazz(User.class);
    }

    @Transactional
    public void save(User user) {
        user.setCreatedDate(DateUtil.now());
        userRepo.save(user);
    }

    @Transactional
    public void update(User user) {
        user.setUpdatedDate(DateUtil.now());
        userRepo.save(user);
    }
}
