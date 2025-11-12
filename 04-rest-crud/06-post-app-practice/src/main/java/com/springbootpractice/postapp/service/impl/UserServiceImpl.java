package com.springbootpractice.postapp.service.impl;

import com.springbootpractice.postapp.dao.UserRepository;
import com.springbootpractice.postapp.entity.User;
import com.springbootpractice.postapp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new RuntimeException("Could not find user with id of " + id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public String deleteUser(int id) {
        repository.deleteById(id);
        return "Successfully deleted user with id of: " + id;
    }
}
