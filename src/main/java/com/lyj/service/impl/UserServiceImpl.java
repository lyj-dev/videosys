package com.lyj.service.impl;

import com.lyj.dao.UserDao;
import com.lyj.entity.User;
import com.lyj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectUser(User user) {
        return userDao.selectUser(user);
    }

    /**
     * 检查邮箱是否已经注册
     * @param email
     * @return 如果已经注册则返回1，否则返回0
     */
    @Override
    public int emailExist(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
