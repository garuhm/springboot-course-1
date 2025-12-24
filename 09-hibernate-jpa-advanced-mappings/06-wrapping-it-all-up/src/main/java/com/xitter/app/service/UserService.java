package com.xitter.app.service;

import com.xitter.app.entity.User;
import com.xitter.app.model.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    List<User> findByQuery(String query);

    void save(WebUser user);
}
