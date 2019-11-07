package com.lyj.service;

import com.lyj.entity.User;

public interface UserService {
    User selectUser(User user);

    int emailExist(String email);

    void insert(User user);

    void update(User user);
}
