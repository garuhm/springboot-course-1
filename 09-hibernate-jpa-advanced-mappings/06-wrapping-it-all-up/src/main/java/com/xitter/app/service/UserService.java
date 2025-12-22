package com.xitter.app.service;

import com.xitter.app.entity.User;
import com.xitter.app.model.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    void save(WebUser user);
}
