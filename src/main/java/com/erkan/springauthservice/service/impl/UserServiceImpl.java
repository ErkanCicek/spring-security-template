package com.erkan.springauthservice.service.impl;

import java.util.Optional;

import com.erkan.springauthservice.entity.User;

public interface UserServiceImpl {
    Optional<User> findByUsername(String username);
}
