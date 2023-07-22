package com.erkan.springauthservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.erkan.springauthservice.entity.User;
import com.erkan.springauthservice.repo.UserRepo;
import com.erkan.springauthservice.service.impl.UserServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserServiceImpl{

    private final UserRepo userRepo;
    
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
}
