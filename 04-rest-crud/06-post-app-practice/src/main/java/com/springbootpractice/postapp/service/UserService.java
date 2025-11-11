package com.springbootpractice.postapp.service;

import com.springbootpractice.postapp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(int id);
    User createUser(User user);
    String deleteUser(int id);
}