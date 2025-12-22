package com.xitter.app.dao;

import com.xitter.app.entity.User;

public interface UserDAO {
    User findUserByName(String userName);

    void save(User user);
}
