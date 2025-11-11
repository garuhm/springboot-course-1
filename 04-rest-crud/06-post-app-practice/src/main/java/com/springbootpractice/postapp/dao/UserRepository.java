package com.springbootpractice.postapp.dao;

import com.springbootpractice.postapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
