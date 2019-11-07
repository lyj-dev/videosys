package com.lyj.dao;

import com.lyj.entity.User;

public interface UserDao {
    User selectUser(User user);

    int selectUserByEmail(String email);

    void insert(User user);

    void update(User user);
}
