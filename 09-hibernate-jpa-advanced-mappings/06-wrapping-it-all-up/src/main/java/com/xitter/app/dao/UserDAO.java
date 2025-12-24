package com.xitter.app.dao;

import com.xitter.app.entity.User;

import java.util.List;

public interface UserDAO {
    User findUserByName(String userName);
    List<User> findByQuery (String query);

    void save(User user);
}
